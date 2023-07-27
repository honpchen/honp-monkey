package ink.honp.core.enums;

import ink.honp.core.entity.ErrorCode;

/**
 * @author jeff chen
 * @since 2023-07-27 10:12
 */
public enum CommonErrorCode implements ErrorCode {

    SYSTEM_ERROR("00001", "系统异常"),
    ILLEGAL_PARAMETER("00004", "非法参数");

    private final String code;
    private final String message;

    CommonErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
