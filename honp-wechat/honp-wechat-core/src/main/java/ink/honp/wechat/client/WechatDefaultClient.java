package ink.honp.wechat.client;

import ink.honp.core.util.JsonUtil;
import ink.honp.wechat.client.executor.WechatByteRequestExecutor;
import ink.honp.wechat.client.executor.WechatGetRequestExecutor;
import ink.honp.wechat.client.executor.WechatPostRequestExecutor;
import ink.honp.wechat.config.WechatConfig;
import ink.honp.wechat.enums.WechatError;
import ink.honp.wechat.exception.WechatException;
import ink.honp.wechat.client.executor.WechatRequestExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
public class WechatDefaultClient implements WechatClient {

    // 失败最大请求次数
    private static final int MAX_RETRY_TIME = 3;

    // 默认执行器实现
    private static final WechatRequestExecutor<String> GET_EXECUTOR = new WechatGetRequestExecutor();
    private static final WechatRequestExecutor<String> POST_EXECUTOR = new WechatPostRequestExecutor();
    private static final WechatRequestExecutor<byte[]> BYTE_EXECUTOR = new WechatByteRequestExecutor();

    private final WechatConfig wechatConfig;

    public WechatDefaultClient(WechatConfig wechatConfig) {
        this.wechatConfig = wechatConfig;
    }

    @Override
    public WechatConfig getConfig() {
        return wechatConfig;
    }

    @Override
    public <T> T get(String url, Object params, Class<T> responseClz) throws WechatException {
        String content = execute(GET_EXECUTOR, url, params, true);

        return JsonUtil.toBean(content, responseClz);
    }

    @Override
    public <T> T get(String url, Object params, boolean requiredAccessToken, Class<T> responseClz) throws WechatException {

        String content = execute(GET_EXECUTOR, url, params, requiredAccessToken);

        return JsonUtil.toBean(content, responseClz);
    }

    @Override
    public <T> T post(String url, Object params, Class<T> responseClz) throws WechatException {
        String content = execute(POST_EXECUTOR, url, params, true);

        return JsonUtil.toBean(content, responseClz);
    }

    @Override
    public <T> T post(String url, Object params, boolean requiredAccessToken, Class<T> responseClz) throws WechatException {
        String content = execute(POST_EXECUTOR, url, params, requiredAccessToken);

        return JsonUtil.toBean(content, responseClz);
    }

    @Override
    public byte[] postBytes(String url, Object params) throws WechatException {

        return execute(BYTE_EXECUTOR, url, params, true);
    }

    @Override
    public <R> R execute(WechatRequestExecutor<R> executor, String url, Object params, boolean requiredAccessToken)
            throws WechatException {

        int retryTime = 0;
        while (retryTime < MAX_RETRY_TIME) {
            retryTime++;
            try {
                return requiredAccessToken ? executeRequiredAccessToken(executor, url, params, true)
                        : executor.execute(url, params);
            } catch (WechatException ex) {

                // -1 系统繁忙, 1000ms后重试
                if (WechatError.SERVER_ERROR.getCode() == ex.getErrCode()) {
                    int sleepMillis = 1000 * (1 << retryTime);
                    try {
                        log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTime);
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw ex;
                }
            }
        }

        log.warn("重试达到最大次数【{}】", MAX_RETRY_TIME);
        throw new WechatException(WechatError.SERVER_ERROR);
    }

    private <R> R executeRequiredAccessToken(WechatRequestExecutor<R> executor, String url,
                                             Object parameters, boolean autoRefreshToken) throws WechatException {

        String accessToken = wechatConfig.getAccessToken(false);
        String urlWithAccessToken = url + (url.contains("?") ? "&" : "?") + "access_token=" + accessToken;
        try {
            return executor.execute(urlWithAccessToken, parameters);
        }catch (WechatException ex) {
            // token 无效, 重新获取 token
            if (accessTokenIsInvalid(ex.getErrCode())) {
                // 强制 accessToken 过期，下次请求重新获取
                expireAccessToken(accessToken);

                if (autoRefreshToken) {
                    log.warn("重新刷新 accessToken, errcode:{}, errmsg:{}", ex.getErrCode(), ex.getMessage());
                    return executeRequiredAccessToken(executor, url, parameters, false);
                }
            }

            throw ex;
        }catch (Exception ex) {
           throw new WechatException(WechatError.SERVER_ERROR, ex);
        }
    }


    private void expireAccessToken(String accessToken) {
        Lock accessTokenLock = wechatConfig.getAccessTokenLock();
        if (null == accessTokenLock) {
            if (accessToken.equals(wechatConfig.getAccessToken())) {
                wechatConfig.expireAccessToken();
            }
        } else {
            accessTokenLock.lock();
            try {
                if (accessToken.equals(wechatConfig.getAccessToken())) {
                    wechatConfig.expireAccessToken();
                }
            } finally {
                accessTokenLock.unlock();
            }
        }
    }


    private boolean accessTokenIsInvalid(Integer code) {
        return null == code
                || WechatError.INVALID_TOKEN.getCode() == code
                || WechatError.ILLEGAL_TOKEN.getCode() == code
                || WechatError.TOKEN_TIMEOUT.getCode() == code;
    }

}
