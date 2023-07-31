package ink.honp.wechat.ma.service.impl;

import ink.honp.wechat.client.WechatClient;
import ink.honp.wechat.client.WechatDefaultClient;
import ink.honp.wechat.exception.WechatException;
import ink.honp.wechat.ma.WechatMaBaseTest;
import ink.honp.wechat.ma.entity.response.WechatMaSessionInfoResponse;
import ink.honp.wechat.ma.service.WechatMaLoginService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
class WechatMaLoginServiceImplTest extends WechatMaBaseTest {

    @Test
    @DisplayName("测试小程序登录")
    void testCode2Session() {
        WechatClient wechatClient = new WechatDefaultClient(getMaConfig());

        WechatMaLoginService wechatMaLoginService = new WechatMaLoginServiceImpl(wechatClient);
        WechatMaSessionInfoResponse sessionInfo = wechatMaLoginService.code2Session("0a1XZB00045PpQ1E6n000KCik71XZB05");

        Assertions.assertNotNull(sessionInfo);

    }

    @Test
    @DisplayName("测试错误 jsCode 登录")
    void testErrorCode2Session() {
        WechatClient wechatClient = new WechatDefaultClient(getMaConfig());

        WechatMaLoginService wechatMaLoginService = new WechatMaLoginServiceImpl(wechatClient);
        WechatException wechatException = Assertions.assertThrows(WechatException.class, () -> {
            wechatMaLoginService.code2Session("0a1XZB00045PpQ1E6n000KCik71XZB05");
        });

        log.info("errCode:{}, errMsg:{}", wechatException.getErrCode(), wechatException.getMessage());

    }
}
