package ink.honp.core.enums;

import ink.honp.core.entity.ResponseCode;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public enum SuccessCode implements ResponseCode {

    SUCCESS("00000", "success");

    private final String code;

    private final String message;

    SuccessCode(String code, String message) {
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
