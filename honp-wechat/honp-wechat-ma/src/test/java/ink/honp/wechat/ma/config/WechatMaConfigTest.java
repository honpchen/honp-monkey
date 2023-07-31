package ink.honp.wechat.ma.config;

import ink.honp.wechat.config.WechatConfig;
import ink.honp.wechat.ma.WechatMaBaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
class WechatMaConfigTest extends WechatMaBaseTest {

    @Test
    @DisplayName("测试获取微信小程序 accessToken")
    void testGetAccessToken() {
        WechatConfig wechatConfig = new WechatMaConfig(APP_ID, APP_SECRET);
        String accessToken = wechatConfig.getAccessToken();

        Assertions.assertNotNull(accessToken);
        ACCESS_TOKEN = accessToken;
        log.info("小程序 accessToken:{}", accessToken);
    }

    @Test
    @DisplayName("测试强制刷新 accessToken")
    void testForceRefreshAccessToken() {
        WechatConfig wechatConfig = new WechatMaConfig(APP_ID, APP_SECRET);
        String accessToken = wechatConfig.getAccessToken(true);

        Assertions.assertNotNull(accessToken);

        ACCESS_TOKEN = accessToken;
        log.info("小程序 accessToken:{}", accessToken);
    }
}
