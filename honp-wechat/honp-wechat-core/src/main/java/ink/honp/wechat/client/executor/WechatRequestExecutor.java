package ink.honp.wechat.client.executor;

import ink.honp.core.util.JsonUtil;
import ink.honp.wechat.entity.WechatResponse;
import ink.honp.wechat.enums.WechatError;
import ink.honp.wechat.exception.WechatException;

/**
 * 请求执行器
 * @author jeff chen
 * @since 2023-07-28 16:46
 */
public interface WechatRequestExecutor<R> {

    /**
     * 微信请求执行实现
     * @param url         请求地址
     * @param parameters  请求参数
     * @return R          返回类型
     * @throws WechatException 执行错误或微信请求失败
     */
    R execute(String url, Object parameters) throws WechatException;

    /**
     * 检查响应内容
     * @param content           响应内容
     * @throws WechatException  content == null && errCode != 0 失败抛出异常
     */
    default void checkResponseContent(String content) throws WechatException {
        WechatResponse response = JsonUtil.toBean(content, WechatResponse.class);
        if (null == response) {
            throw new WechatException(WechatError.SERVER_ERROR);
        }
        if (!response.isSuccess()) {
            throw new WechatException(response.getErrcode(), response.getErrmsg());
        }
    }
}
