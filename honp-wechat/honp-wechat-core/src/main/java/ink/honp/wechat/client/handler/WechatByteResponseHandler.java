package ink.honp.wechat.client.handler;

import ink.honp.core.http.handler.ResponseHandler;
import ink.honp.core.util.JsonUtil;
import ink.honp.wechat.entity.WechatResponse;
import ink.honp.wechat.enums.WechatError;
import ink.honp.wechat.exception.WechatException;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Optional;

/**
 * 微信字节响应处理
 * @author jeff chen
 * @since 2023-07-30 23:20
 */
public class WechatByteResponseHandler implements ResponseHandler<byte[]> {

    @Override
    public Optional<byte[]> handle(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        if (null == responseBody) {
            throw new WechatException(WechatError.SERVER_ERROR);
        }

        // 判断响应是否是 json 内容，若是则请求失败
        MediaType mediaType = responseBody.contentType();
        if (mediaType == null || !mediaType.toString().contains("json")) {
            return Optional.of(responseBody.bytes());
        }

        WechatResponse wechatResponse = JsonUtil.toBean(responseBody.string(), WechatResponse.class);
        if (null == wechatResponse) {
            throw new WechatException(WechatError.SERVER_ERROR);
        }

        throw new WechatException(wechatResponse.getErrcode(), wechatResponse.getErrmsg());
    }
}
