package ink.honp.wechat.ma.entity.request;

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
public class WechatMaUnlimitedQrcodeGetRequest {

    /**
     * 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：
     * !#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     */
    @JsonProperty("scene")
    private String scene;

    /** 扫码进入的小程序页面路径，最大长度 128 字节，不能为空 **/
    @JsonProperty("page")
    private String page;

    /** 默认是true，检查page 是否存在 **/
    @JsonProperty("check_path")
    private Boolean checkPath;

    /** 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"。默认是正式版 **/
    @JsonProperty("env_version")
    private String envVersion;

    /** 二维码的宽度，单位 px。默认值为430，最小 280px，最大 1280px **/
    @JsonProperty("width")
    private Integer width = 430;

    /** 默认值false；自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调 **/
    @JsonProperty("auto_color")
    private boolean autoColor;

    /**
     * 默认值{"r":0,"g":0,"b":0} ；auto_color 为 false 时生效，
     * 使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示
     */
    @JsonProperty("line_color")
    private WechatMaLineColorRequest lineColor;

    /** 默认值false；是否需要透明底色 **/
    @JsonProperty("is_hyaline")
    private boolean isHyaline;
}
