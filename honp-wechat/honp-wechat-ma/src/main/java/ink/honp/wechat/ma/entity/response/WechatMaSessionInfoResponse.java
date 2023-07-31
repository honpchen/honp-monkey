package ink.honp.wechat.ma.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ink.honp.wechat.entity.WechatResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jeff chen
 * @since 2023-07-30 23:36
 */
@Getter
@Setter
@ToString
public class WechatMaSessionInfoResponse extends WechatResponse {

    /** 会话密钥 **/
    @JsonProperty("session_key")
    private String sessionKey;

    /** 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回 **/
    @JsonProperty("unionid")
    private String unionid;

    /** 用户唯一标识 **/
    @JsonProperty("openid")
    private String openid;
}
