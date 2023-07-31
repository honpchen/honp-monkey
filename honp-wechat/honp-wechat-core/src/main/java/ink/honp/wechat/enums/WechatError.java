package ink.honp.wechat.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author jeff chen
 * @since 2023-07-28 16:08
 */
@Getter
@ToString
public enum WechatError {

    INVALID_TOKEN(40001, "access_token 无效"),
    ILLEGAL_TOKEN(40014, "不合法的 access_token"),
    TOKEN_TIMEOUT(42001, "access_token 超时"),
    SERVER_ERROR(-1, "系统繁忙"),
    GET_ACCESS_TOKEN_ERROR(-11, "获取 accessToken 失败"),
    CONFIG_ERROR(-12, "配置错误");

    private final int code;
    private final String message;

    WechatError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
