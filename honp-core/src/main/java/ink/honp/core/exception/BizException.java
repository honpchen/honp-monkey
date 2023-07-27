package ink.honp.core.exception;

import ink.honp.core.entity.ErrorCode;
import lombok.Getter;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
public class BizException extends RuntimeException {

    private final String errCode;

    public BizException(ErrorCode error) {
        super(error.getMessage());
        this.errCode = error.getCode();
    }

    public BizException(ErrorCode error, Throwable cause) {
        super(error.getMessage(), cause);
        this.errCode = error.getCode();
    }

    public BizException(String errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
    }

    public BizException(String errCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errCode = errCode;
    }

}
