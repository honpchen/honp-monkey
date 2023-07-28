package ink.honp.wechat.request.executor;

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
}
