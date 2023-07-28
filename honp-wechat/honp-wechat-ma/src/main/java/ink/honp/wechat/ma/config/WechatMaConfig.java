package ink.honp.wechat.ma.config;

import ink.honp.core.util.DateUtil;
import ink.honp.wechat.config.WechatAbstractConfig;
import ink.honp.wechat.entity.WechatTokenInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class WechatMaConfig extends WechatAbstractConfig {

    private final String appId;
    private final String appSecret;

    private String accessToken;
    private long accessTokenExpiresTime = 0L;

    public WechatMaConfig(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }


    @Override
    protected String accessToken() {
        return this.accessToken;
    }


    @Override
    public void refreshAccessToken(WechatTokenInfo tokenInfo) {
        this.accessToken = tokenInfo.getAccessToken();
        this.accessTokenExpiresTime = DateUtil.addSeconds(new Date(), tokenInfo.getExpiresIn() - 10).getTime();
    }

    @Override
    public void expireAccessToken() {
        this.accessTokenExpiresTime = 0;
    }

    @Override
    public boolean accessTokenIsExpire() {
        return accessTokenExpiresTime == 0 || StringUtils.isBlank(accessToken);
    }

    @Override
    public WechatTokenInfo doGetAccessToken() {

        return null;
    }
}
