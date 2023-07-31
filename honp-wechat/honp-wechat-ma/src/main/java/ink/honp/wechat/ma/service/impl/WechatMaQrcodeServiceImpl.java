package ink.honp.wechat.ma.service.impl;

import ink.honp.wechat.client.WechatClient;
import ink.honp.wechat.ma.entity.request.WechatMaQrcodeCreateRequest;
import ink.honp.wechat.ma.entity.request.WechatMaQrcodeGetRequest;
import ink.honp.wechat.ma.entity.request.WechatMaUnlimitedQrcodeGetRequest;
import ink.honp.wechat.ma.service.WechatMaQrcodeService;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class WechatMaQrcodeServiceImpl implements WechatMaQrcodeService {

    private final WechatClient wechatClient;

    public WechatMaQrcodeServiceImpl(WechatClient wechatClient) {
        this.wechatClient = wechatClient;
    }

    @Override
    public byte[] getWxMaQrcode(WechatMaQrcodeGetRequest qrcodeGetRequest) {

        return wechatClient.postBytes(QRCODE_GET_API, qrcodeGetRequest);
    }

    @Override
    public byte[] getUnlimitedQrcode(WechatMaUnlimitedQrcodeGetRequest unlimitedQrcodeGetRequest) {

        return wechatClient.postBytes(QRCODE_GET_UNLIMIT_API, unlimitedQrcodeGetRequest);
    }

    @Override
    public byte[] createQrcode(WechatMaQrcodeCreateRequest createRequest) {

        return wechatClient.postBytes(QRCODE_CREATE_API, createRequest);
    }
}
