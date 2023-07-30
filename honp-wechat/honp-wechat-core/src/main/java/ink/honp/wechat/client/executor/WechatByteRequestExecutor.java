package ink.honp.wechat.client.executor;

import ink.honp.core.http.HttpUtil;
import ink.honp.core.http.builder.PostRequestBuilder;
import ink.honp.core.http.handler.ResponseHandler;
import ink.honp.wechat.client.handler.WechatByteResponseHandler;
import ink.honp.wechat.exception.WechatException;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 请求执行器
 * @author jeff chen
 * @since 2023-07-28 16:46
 */
public class WechatByteRequestExecutor implements WechatRequestExecutor<byte[]> {

    private static final Map<String, String> EMPTY_HANDLER = new HashMap<>();
    private static final WechatByteResponseHandler BYTE_RESPONSE_HANDLER = new WechatByteResponseHandler();

    @Override
    public byte[] execute(String url, Object parameters) throws WechatException {
        PostRequestBuilder requestBuilder = PostRequestBuilder.create(url, EMPTY_HANDLER, parameters, false);

        return HttpUtil.execute(requestBuilder, BYTE_RESPONSE_HANDLER);
    }

}
