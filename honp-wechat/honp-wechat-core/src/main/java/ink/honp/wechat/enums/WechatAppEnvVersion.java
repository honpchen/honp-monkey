package ink.honp.wechat.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 微信应用环境版本
 * @author jeff chen
 * @since 2023-07-28 15:02
 */
@Getter
@ToString
public enum WechatAppEnvVersion {

    /**
     * 正式版
     */
    RELEASE("release"),

    /**
     * 体验版
     */
    TRIAL("trial"),

    /**
     * 开发版
     */
    DEVELOP("develop"),
    ;

    private final String code;

    WechatAppEnvVersion(String code) {
        this.code = code;
    }
}
