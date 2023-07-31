package ink.honp.wechat.ma.config;

import ink.honp.core.http.HttpUtil;
import ink.honp.core.util.DateUtil;
import ink.honp.core.util.JsonUtil;
import ink.honp.wechat.config.WechatAbstractConfig;
import ink.honp.wechat.constant.WechatConstant;
import ink.honp.wechat.entity.WechatTokenInfo;
import ink.honp.wechat.enums.WechatError;
import ink.honp.wechat.enums.WechatGrantType;
import ink.honp.wechat.exception.WechatException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
public class WechatMaConfig extends WechatAbstractConfig {

    private static final String STABLE_TOKEN_API = WechatConstant.API_SERVER_DOMAIN + "/cgi-bin/stable_token";

    private final String appId;
    private final String appSecret;

    private String accessToken;
    private long accessTokenExpiresTime = 0L;

    public WechatMaConfig(@NonNull String appId, @NonNull String appSecret) {
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
        Map<String, String> requestParams = new HashMap<>(4);
        requestParams.put("appid", appId);
        requestParams.put("secret", appSecret);
        requestParams.put("grant_type", WechatGrantType.CLIENT_CREDENTIAL.getCode());

        String result = HttpUtil.post(STABLE_TOKEN_API, requestParams);
        WechatTokenInfo response = JsonUtil.toBean(result, WechatTokenInfo.class);
        if (null == response) {
            log.error("微信小程序 access_token 获取失败：{}", result);
            throw new WechatException(WechatError.SERVER_ERROR);
        }

        if (response.isSuccess()) {
            return response;
        }

        throw new WechatException(response.getErrcode(), response.getErrmsg());
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }
}
