package ink.honp.wechat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class WechatTokenInfo extends WechatResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;
}
