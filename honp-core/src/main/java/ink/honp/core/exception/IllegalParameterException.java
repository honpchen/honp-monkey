package ink.honp.core.exception;

import ink.honp.core.enums.CommonErrorCode;

/**
 * 非法参数异常
 * @author jeff chen
 * @since 1.0.0
 */
public class IllegalParameterException extends BaseException {

    public IllegalParameterException(String message) {
        super(CommonErrorCode.ILLEGAL_PARAMETER.getCode(), message);
    }
}
