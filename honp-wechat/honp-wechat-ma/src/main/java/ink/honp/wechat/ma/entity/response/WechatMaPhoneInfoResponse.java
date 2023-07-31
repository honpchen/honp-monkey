package ink.honp.wechat.ma.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import ink.honp.wechat.entity.WechatResponse;
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
public class WechatMaPhoneInfoResponse extends WechatResponse {

    /** 用户手机号信息 **/
    @JsonProperty("phone_info")
    private PhoneInfo phoneInfo;

    @Getter
    @Setter
    @ToString
    public static class PhoneInfo {
        /** 用户绑定的手机号（国外手机号会有区号） **/
        private String phoneNumber;

        /** 没有区号的手机号 **/
        private String purePhoneNumber;

        /** 区号 **/
        private String countryCode;

        @Getter
        @Setter
        @ToString
        public static class Watermark {

            /** 用户获取手机号操作的时间戳 **/
            private Long timestamp;

            /** 小程序appid **/
            private String appid;
        }
    }
}
