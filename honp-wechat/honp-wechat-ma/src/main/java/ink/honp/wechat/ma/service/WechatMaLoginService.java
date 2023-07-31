package ink.honp.wechat.ma.service;

import ink.honp.wechat.constant.WechatConstant;
import ink.honp.wechat.ma.entity.response.WechatMaSessionInfoResponse;

/**
 * 小程序登录
 * @author jeff chen
 * @since 2023-07-30 14:27
 */
public interface WechatMaLoginService {

    String CODE_2_SESSION_API = WechatConstant.API_SERVER_DOMAIN + "/sns/jscode2session";

    /**
     * 登录凭证校验
     * @param jsCode 临时登录凭证 code
     * @return {@link WechatMaSessionInfoResponse}
     */
    WechatMaSessionInfoResponse code2Session(String jsCode);
}
