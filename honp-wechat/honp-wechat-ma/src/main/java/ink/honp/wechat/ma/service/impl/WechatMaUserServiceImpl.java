package ink.honp.wechat.ma.service.impl;

import ink.honp.wechat.client.WechatClient;
import ink.honp.wechat.ma.entity.response.WechatMaPhoneInfoResponse;
import ink.honp.wechat.ma.service.WechatMaUserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class WechatMaUserServiceImpl implements WechatMaUserService {

    private final WechatClient wechatClient;

    public WechatMaUserServiceImpl(WechatClient wechatClient) {
        this.wechatClient = wechatClient;
    }

    @Override
    public WechatMaPhoneInfoResponse getPhoneNumber(String code) {
        Map<String, String> requestParams = new HashMap<>(2);
        requestParams.put("code", code);

        return wechatClient.post(GET_PHONE_NUMBER_API, requestParams, WechatMaPhoneInfoResponse.class);
    }
}
