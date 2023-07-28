package ink.honp.core.exception;

import ink.honp.core.entity.ErrorCode;
import lombok.Getter;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
public class BaseException extends RuntimeException {

    private final String errCode;

    public BaseException(ErrorCode error) {
        super(error.getMessage());
        this.errCode = error.getCode();
    }

    public BaseException(ErrorCode error, Throwable cause) {
        super(error.getMessage(), cause);
        this.errCode = error.getCode();
    }

    public BaseException(String errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
    }

    public BaseException(String errCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errCode = errCode;
    }

}
