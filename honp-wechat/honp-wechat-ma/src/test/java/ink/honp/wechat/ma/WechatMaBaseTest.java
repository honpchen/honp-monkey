package ink.honp.wechat.ma;

import ink.honp.wechat.client.WechatClient;
import ink.honp.wechat.client.WechatDefaultClient;
import ink.honp.wechat.ma.config.WechatMaConfig;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class WechatMaBaseTest {

    public static final String APP_ID = "appid";
    public static final String APP_SECRET = "appSecret";
    public static String ACCESS_TOKEN = "71_M8_ipnTI8NJtDUFUqyprVLgg2d5TY8pI9wMbPUWIz9pWQnbYsScUQmW7XZdNMGlXa-fudy3JRjBQGrJ2CO6pUYQR5gWjTmCecNEOwMb-fCOLYpXjv0mrSK-oRHMOHQiABAJCL";


    public WechatMaConfig getMaConfig() {
        return new WechatMaConfig(APP_ID, APP_SECRET);
    }

    public WechatClient getWechatClient() {
        return new WechatDefaultClient(getMaConfig());
    }
}
