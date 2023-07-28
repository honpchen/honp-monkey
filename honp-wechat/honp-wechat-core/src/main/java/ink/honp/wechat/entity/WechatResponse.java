package ink.honp.wechat.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信 API 响应状态
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class WechatResponse {

    /** 错误码 **/
    private int errcode;

    /** 错误信息 **/
    private String errmsg;

    public boolean isSuccess() {

        return 0 == errcode;
    }

}
