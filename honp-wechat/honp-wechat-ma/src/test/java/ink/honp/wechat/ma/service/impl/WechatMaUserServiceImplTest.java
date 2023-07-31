package ink.honp.wechat.ma.service.impl;

import ink.honp.wechat.ma.WechatMaBaseTest;
import ink.honp.wechat.ma.service.WechatMaUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jeff chen
 * @since 1.0.0
 */
class WechatMaUserServiceImplTest extends WechatMaBaseTest {

    @Test
    @DisplayName("测试手机号码校验")
    void testGetPhoneNumber() {
        WechatMaUserService wechatMaUserService = new WechatMaUserServiceImpl(getWechatClient());

        wechatMaUserService.getPhoneNumber("");
    }

}
