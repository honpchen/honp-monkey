package ink.honp.core.http.interceptor;

import ink.honp.core.http.HttpConstant;
import ink.honp.core.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Http 日志拦截器
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
public class HttpLogInterceptor implements Interceptor {


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain)  throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);

        if (log.isDebugEnabled()) {
            log.debug("请求地址: {}", request.url());
            log.debug("请求头部: {}", getHeaders(request));
            log.debug("请求参数: {}", getRequestParams(request));
            log.debug("响应状态: {}", response.code());
        }

        return response;
    }

    private String getHeaders(Request request) {
        StringBuilder headerContent = new StringBuilder();
        Headers headers = request.headers();
        headers.forEach(pair -> headerContent.append(pair.getFirst()).append(":").append(pair.getSecond()).append(","));
        return headerContent.toString();
    }


    private String getRequestParams(Request request) {
        if (HttpMethod.GET.getCode().equalsIgnoreCase(request.method())) {
            return StringUtils.EMPTY;
        }

        if (HttpConstant.APPLICATION_JSON.equals(request.header(HttpConstant.CONTENT_TYPE))) {
            return "上传文件";
        }

        RequestBody requestBody = request.body();
        if (null != requestBody) {
            try {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                return buffer.readString(StandardCharsets.UTF_8);
            } catch (IOException e) {
                log.warn("请求参数解析异常: {}", e.getMessage(), e);
            }
        }

        return StringUtils.EMPTY;
    }
}
