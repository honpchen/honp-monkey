package ink.honp.wechat.ma.service;

import ink.honp.wechat.constant.WechatConstant;
import ink.honp.wechat.ma.entity.response.WechatMaPhoneInfoResponse;

/**
 * 用户信息
 * @author jeff chen
 * @since 2023-07-31 15:01
 */
public interface WechatMaUserService {

    String GET_PHONE_NUMBER_API = WechatConstant.API_SERVER_DOMAIN + "/wxa/business/getuserphonenumber";

    /**
     * 手机号验证
     * @param code -
     * @return 用户手机号码信息
     */
    WechatMaPhoneInfoResponse getPhoneNumber(String code);
}
