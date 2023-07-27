package ink.honp.core.exception;

import ink.honp.core.enums.CommonErrorCode;
import lombok.Getter;

/**
 * 非法参数异常
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
public class IllegalParameterException extends RuntimeException {

    private final String errCode = CommonErrorCode.ILLEGAL_PARAMETER.getCode();

    public IllegalParameterException(String message) {
        super(message);
    }
}
