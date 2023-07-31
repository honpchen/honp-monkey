package ink.honp.wechat.ma.service;

import ink.honp.wechat.constant.WechatConstant;
import ink.honp.wechat.ma.entity.request.WechatMaQrcodeCreateRequest;
import ink.honp.wechat.ma.entity.request.WechatMaQrcodeGetRequest;
import ink.honp.wechat.ma.entity.request.WechatMaUnlimitedQrcodeGetRequest;

/**
 * @author jeff chen
 * @since 2023-07-31 15:28
 */
public interface WechatMaQrcodeService {

    String QRCODE_GET_API = WechatConstant.API_SERVER_DOMAIN + "/wxa/getwxacode";
    String QRCODE_GET_UNLIMIT_API = WechatConstant.API_SERVER_DOMAIN + "/wxa/getwxacodeunlimit";
    String QRCODE_CREATE_API = WechatConstant.API_SERVER_DOMAIN + "/cgi-bin/wxaapp/createwxaqrcode";

    /**
     * 获取小程序码
     * @param qrcodeGetRequest 二维码参数
     * @return 二维码图片字节流
     */
    byte[] getWxMaQrcode(WechatMaQrcodeGetRequest qrcodeGetRequest);

    /**
     * 获取不限制的小程序码
     * @param unlimitedQrcodeGetRequest 二维码参数
     * @return 二维码图片字节流
     */
    byte[] getUnlimitedQrcode(WechatMaUnlimitedQrcodeGetRequest unlimitedQrcodeGetRequest);

    /**
     * 获取小程序二维码
     * @param createRequest -
     * @return 二维码图片字节流
     */
    byte[] createQrcode(WechatMaQrcodeCreateRequest createRequest);
}
