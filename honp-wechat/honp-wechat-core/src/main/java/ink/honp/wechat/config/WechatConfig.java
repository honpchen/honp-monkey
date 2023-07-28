package ink.honp.wechat.config;

import ink.honp.wechat.entity.WechatTokenInfo;
import ink.honp.wechat.exception.WechatException;

import java.util.concurrent.locks.Lock;

/**
 * @author jeff chen
 * @since 2023-07-28 15:06
 */
public interface WechatConfig {

    /**
     * 获取 accessToken
     * @return accessToken
     * @throws WechatException 获取 accessToken失败
     */
    String getAccessToken() throws WechatException;

    /**
     * 获取 accessToken
     * @param forceRefresh 是否强制刷新，{@code true} 强制刷新
     * @return accessToken
     * @throws WechatException 获取 accessToken失败
     */
    String getAccessToken(boolean forceRefresh) throws WechatException;

    /**
     * accessToken 锁
     * @return 可返回null, 若返回 null，则表示无锁实现
     */
    Lock getAccessTokenLock();

    /**
     * accessToken 是否过期
     * @return {@code true} 已过期
     */
    boolean accessTokenIsExpire();

    /**
     * 刷新 accessToken
     * @param tokenInfo -
     */
    void refreshAccessToken(WechatTokenInfo tokenInfo);

    /**
     * 强制 accessToken 过期
     */
    void expireAccessToken();
}
