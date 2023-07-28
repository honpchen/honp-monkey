package ink.honp.core.http.handler;

import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Optional;

/**
 * 响应返回字符串
 * @author jeff chen
 * @since 1.0.0
 */
public class StringResponseHandler implements ResponseHandler<String> {

    @Override
    public Optional<String> handle(Response response) throws IOException {
        ResponseBody body = response.body();
        if (null == body) {
            return Optional.empty();
        }

        return Optional.of(body.string());
    }
}
