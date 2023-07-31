package ink.honp.wechat.config;

import ink.honp.wechat.entity.WechatTokenInfo;
import ink.honp.wechat.enums.WechatError;
import ink.honp.wechat.exception.WechatException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author jeff chen
 * @since 2023-07-28 16:17
 */
public abstract class WechatAbstractConfig implements WechatConfig {


    @Override
    public String getAccessToken() {
        return getAccessToken(false);
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws WechatException {
        if (!forceRefresh && !accessTokenIsExpire()) {
            return accessToken();
        }

        Lock accessTokenLock = getAccessTokenLock();
        if (accessTokenLock == null) {
            WechatTokenInfo tokenInfo = doGetAccessToken();
            refreshAccessToken(tokenInfo);

            return tokenInfo.getAccessToken();
        }

        boolean isLock = false;
        try {
            do {
                isLock = getAccessTokenLock().tryLock(100, TimeUnit.MILLISECONDS);
                if (!forceRefresh && !accessTokenIsExpire()) {
                    return accessToken();
                }
            }while (!isLock);
            WechatTokenInfo tokenInfo = doGetAccessToken();
            refreshAccessToken(tokenInfo);

            return tokenInfo.getAccessToken();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (isLock) {
                getAccessTokenLock().unlock();
            }
        }

        throw new WechatException(WechatError.GET_ACCESS_TOKEN_ERROR);
    }

    @Override
    public Lock getAccessTokenLock() {
        return null;
    }


    protected abstract String accessToken();

    /**
     * 实际调用微信API获取 accessToken
     * @return tokenInfo
     */
    public abstract WechatTokenInfo doGetAccessToken();
}
