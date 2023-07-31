package ink.honp.wechat.client;

import ink.honp.wechat.client.executor.WechatRequestExecutor;
import ink.honp.wechat.config.WechatConfig;
import ink.honp.wechat.exception.WechatException;

/**
 * 微信 API 接口表示
 * @author jeff chen
 * @since 2023-07-29 23:47
 */
public interface WechatClient {

    /**
     * 获取配置信息
     * @return -
     */
    WechatConfig getConfig();


    /**
     * GET 请求, 该请求会自动填充 access_token
     * @param url           请求地址
     * @param params        请求参数
     * @param responseClz   响应类型
     * @param <T>           -
     * @return              返回指定响应实例
     * @throws WechatException  请求失败
     */
    <T> T get(String url, Object params, Class<T> responseClz) throws WechatException;

    /**
     * GET 请求, 该请求会自动填充 access_token
     * @param url           请求地址
     * @param params        请求参数
     * @param requiredAccessToken 是否要求 access_token
     * @param responseClz   响应类型
     * @param <T>           -
     * @return              返回指定响应实例
     * @throws WechatException  请求失败
     */
    <T> T get(String url, Object params, boolean requiredAccessToken, Class<T> responseClz) throws WechatException;

    /**
     * POST 请求, 该请求会自动填充 access_token
     * @param url           请求地址
     * @param params        请求参数
     * @param responseClz   响应类型
     * @param <T>           -
     * @return              返回指定响应实例
     * @throws WechatException  请求失败
     */
    <T> T post(String url, Object params, Class<T> responseClz) throws WechatException;

    /**
     * POST 请求, 该请求会自动填充 access_token
     * @param url           请求地址
     * @param params        请求参数
     * @param requiredAccessToken 是否要求 access_token
     * @param responseClz   响应类型
     * @param <T>           -
     * @return              返回指定响应实例
     * @throws WechatException  请求失败
     */
    <T> T post(String url, Object params, boolean requiredAccessToken, Class<T> responseClz) throws WechatException;

    /**
     * POST 请求获取字节内容, 该请求会自动填充 access_token
     * @param url           请求地址
     * @param params        请求参数
     * @return              响应为字节内容
     * @throws WechatException  请求失败
     */
    byte[] postBytes(String url, Object params) throws WechatException;

    /**
     * 微信请求执行
     * @param executor 执行器
     * @param url      请求地址
     * @param params   请求参数
     * @param requiredAccessToken 请求是否需要添加 accessToken
     * @return -
     * @param <R> 返回类型
     * @throws WechatException 执行错误或微信请求失败
     */
    <R> R execute(WechatRequestExecutor<R> executor, String url, Object params, boolean requiredAccessToken)
            throws WechatException;
}
