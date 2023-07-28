package ink.honp.core.exception;

import ink.honp.core.enums.CommonErrorCode;

/**
 * 系统异常
 * @author jeff chen
 * @since 1.0.0
 */
public class SystemException extends BaseException {

    public SystemException(String message) {
        super(CommonErrorCode.SYSTEM_ERROR.getCode(), message);
    }

    public SystemException(String message, Throwable cause) {
        super(CommonErrorCode.SYSTEM_ERROR.getCode(), message, cause);
    }
}
