package ink.honp.wechat.ma.entity.request;

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
public class WechatMaQrcodeCreateRequest {

    /**
     * 扫码进入的小程序页面路径，最大长度 128 字节，不能为空；对于小游戏，
     * 可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
     */
    private String path;

    /**
     * 二维码的宽度，单位 px。最小 280px，最大 1280px
     */
    private Integer width = 430;
}
