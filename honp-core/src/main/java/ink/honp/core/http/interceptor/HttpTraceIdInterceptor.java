package ink.honp.core.http.interceptor;

import lombok.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * 请求链路拦截器
 * @author jeff chen
 * @since 2023/1/14 12:03
 */
public class HttpTraceIdInterceptor implements Interceptor {

    public static final String TRACE_ID = "trace-id";

    /**
     * traceId 字段名称
     */
    private final String traceKey;

    public HttpTraceIdInterceptor() {
        this(TRACE_ID);
    }

    public HttpTraceIdInterceptor(@NonNull String traceKey) {
        this.traceKey = traceKey;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String traceId = MDC.get(traceKey);
        if (StringUtils.isBlank(traceId)) {
            return chain.proceed(chain.request());
        }

        Request request = chain.request().newBuilder().addHeader(traceKey, traceId).build();
        return chain.proceed(request);
    }
}
