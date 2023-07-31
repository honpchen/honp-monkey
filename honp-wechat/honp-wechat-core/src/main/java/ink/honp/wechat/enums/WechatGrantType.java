package ink.honp.wechat.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author jeff chen
 * @since 2023-07-31 10:16
 */
@Getter
@ToString
public enum WechatGrantType {

    CLIENT_CREDENTIAL("client_credential"),
    AUTHORIZATION_CODE("authorization_code");

    private final String code;

    WechatGrantType(String code) {
        this.code = code;
    }
}
