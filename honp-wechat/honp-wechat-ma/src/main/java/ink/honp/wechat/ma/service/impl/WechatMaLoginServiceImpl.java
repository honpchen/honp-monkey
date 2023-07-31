package ink.honp.wechat.ma.service.impl;

import ink.honp.wechat.client.WechatClient;
import ink.honp.wechat.enums.WechatError;
import ink.honp.wechat.enums.WechatGrantType;
import ink.honp.wechat.exception.WechatException;
import ink.honp.wechat.ma.config.WechatMaConfig;
import ink.honp.wechat.ma.entity.response.WechatMaSessionInfoResponse;
import ink.honp.wechat.ma.service.WechatMaLoginService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
public class WechatMaLoginServiceImpl implements WechatMaLoginService {

    private final WechatClient wechatClient;

    public WechatMaLoginServiceImpl(WechatClient wechatClient) {
        this.wechatClient = wechatClient;
    }

    @Override
    public WechatMaSessionInfoResponse code2Session(String jsCode) {
        WechatMaConfig maConfig = (WechatMaConfig) wechatClient.getConfig();
        if (null == maConfig) {
            log.error("小程序未配置");
            throw new WechatException(WechatError.SERVER_ERROR.getCode(), "小程序未配置");
        }

        Map<String, String> requestParams = new HashMap<>(4);
        requestParams.put("appid", maConfig.getAppId());
        requestParams.put("secret", maConfig.getAppSecret());
        requestParams.put("js_code", jsCode);
        requestParams.put("grant_type", WechatGrantType.AUTHORIZATION_CODE.getCode());

        return wechatClient.get(CODE_2_SESSION_API, requestParams,
                false, WechatMaSessionInfoResponse.class);
    }
}
