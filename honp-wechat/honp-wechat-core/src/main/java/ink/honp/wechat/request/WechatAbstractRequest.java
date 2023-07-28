package ink.honp.wechat.request;

import ink.honp.wechat.config.WechatConfig;
import ink.honp.wechat.enums.WechatError;
import ink.honp.wechat.exception.WechatException;
import ink.honp.wechat.request.executor.WechatRequestExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
public abstract class WechatAbstractRequest {

    private static final int MAX_RETRY_TIME = 3;

    private final WechatConfig wechatConfig;

    protected WechatAbstractRequest(WechatConfig wechatConfig) {
        this.wechatConfig = wechatConfig;
    }

    /**
     * 微信请求执行
     * @param executor 执行器
     * @param url      请求地址
     * @param params   请求参数
     * @return -
     * @param <R> 返回类型
     * @throws WechatException 执行错误或微信请求失败
     */
    public <R> R execute(WechatRequestExecutor<R> executor, String url, Object params) throws WechatException {
        int retryTime = 0;
        while (retryTime < MAX_RETRY_TIME) {
            retryTime++;
            try {
                return doExecute(executor, url, params, true);
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

    private <R> R doExecute(WechatRequestExecutor<R> executor, String url, Object parameters, boolean autoRefreshToken)
            throws WechatException {

        String accessToken = wechatConfig.getAccessToken(false);
        String urlWithAccessToken = url + (url.contains("?") ? "&" : "?") + "access_token=" + accessToken;

        try {
            return executor.execute(urlWithAccessToken, parameters);
        }catch (WechatException ex) {
            // token 无效, 重新获取 token
            if (accessTokenIsInvalid(ex.getErrCode())) {
                Lock accessTokenLock = wechatConfig.getAccessTokenLock();
                if (null == accessTokenLock) {
                    wechatConfig.expireAccessToken();
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

                if (autoRefreshToken) {
                    log.warn("重新刷新 accessToken, errcode:{}, errmsg:{}", ex.getErrCode(), ex.getMessage());
                    return this.doExecute(executor, url, parameters, false);
                }
            }

            throw ex;
        }catch (Exception ex) {
           throw new WechatException(WechatError.SERVER_ERROR, ex);
        }
    }

    private boolean accessTokenIsInvalid(Integer code) {
        return null == code
                || WechatError.INVALID_TOKEN.getCode() == code
                || WechatError.ILLEGAL_TOKEN.getCode() == code
                || WechatError.TOKEN_TIMEOUT.getCode() == code;
    }

}
