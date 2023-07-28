package ink.honp.core.exception;

import ink.honp.core.entity.ErrorCode;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class BizException extends BaseException {

    public BizException(ErrorCode error) {
        super(error);
    }

    public BizException(ErrorCode error, Throwable cause) {
        super(error, cause);
    }

    public BizException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public BizException(String errCode, String errMessage, Throwable cause) {
        super(errMessage, errMessage, cause);
    }

}
