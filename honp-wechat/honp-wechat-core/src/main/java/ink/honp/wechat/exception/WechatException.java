package ink.honp.wechat.exception;

import ink.honp.wechat.entity.WechatResponse;
import ink.honp.wechat.enums.WechatError;
import lombok.Getter;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
public class WechatException extends RuntimeException {

    private final Integer errCode;

    public WechatException(WechatResponse response) {
        super(response.getErrmsg());
        this.errCode = response.getErrcode();
    }

    public WechatException(WechatError error) {
        super(error.getMessage());
        this.errCode = error.getCode();
    }

    public WechatException(WechatError error, Throwable cause) {
        super(error.getMessage(), cause);
        this.errCode = error.getCode();
    }

    public WechatException(Integer errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
    }


    public WechatException(Integer errCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errCode = errCode;
    }
}
