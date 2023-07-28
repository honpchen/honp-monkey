package ink.honp.core.http;

import ink.honp.core.http.builder.*;
import ink.honp.core.http.handler.ResponseHandler;
import ink.honp.core.http.handler.StringResponseHandler;
import ink.honp.core.http.interceptor.HttpLogInterceptor;
import ink.honp.core.http.interceptor.HttpTraceIdInterceptor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
public abstract class HttpUtil {

    /** http 连接超时时间 **/
    private static final Integer CONN_TIMEOUT_SEC = 60;
    private static final Integer READ_TIMEOUT_SEC = 2 * 60;
    private static final Integer WRITE_TIMEOUT_SEC = 2 * 60;

    /** http 连接池配置 **/
    private static final Integer POOL_MAX_IDLE_CONN = 10;
    private static final Long POOL_KEEP_ALIVE_DURATION = 5L;

    private static final Map<String, String> EMPTY_HEADER = new HashMap<>();
    private static final Object NO_REQUEST_PARAMETERS = null;

    // 响应处理器
    private static final StringResponseHandler RESPONSE_HANDLER = new StringResponseHandler();

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(POOL_MAX_IDLE_CONN, POOL_KEEP_ALIVE_DURATION, TimeUnit.MINUTES))
            .addInterceptor(new HttpTraceIdInterceptor())
            .addInterceptor(new HttpLogInterceptor())
            .build();

    private HttpUtil() {
    }

    /**
     * GET 请求
     * @param url 请求地址
     * @return -
     */
    public static String get(@NonNull String url) {

        return execute(GetRequestBuilder.create(url, EMPTY_HEADER, NO_REQUEST_PARAMETERS));
    }

    /**
     * GET 请求
     * @param url    请求地址
     * @param params 请求参数
     * @return -
     */
    public static String get(@NonNull String url, Object params) {

        return execute(GetRequestBuilder.create(url, EMPTY_HEADER, params));
    }

    /**
     * GET 请求
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String get(@NonNull String url, @NonNull Map<String, String> headers, Object params) {

        return execute(GetRequestBuilder.create(url, headers, params));
    }

    /**
     * POST 请求
     * Content-Type: application/json
     * @param url       请求地址
     * @param params    请求参数
     * @return -
     */
    public static String post(@NonNull String url, Object params) {

        return execute(PostRequestBuilder.create(url, EMPTY_HEADER, params, false));
    }


    /**
     * POST 请求
     * Content-Type: application/json
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String post(@NonNull String url, @NonNull Map<String, String> headers, Object params) {

        return execute(PostRequestBuilder.create(url, headers, params, false));
    }

    /**
     * POST 请求
     * Content-Type: application/x-www-form-urlencoded
     * @param url       请求地址
     * @param params    请求参数
     * @return -
     */
    public static String postFrom(@NonNull String url, Object params) {

        return execute(PostRequestBuilder.create(url, EMPTY_HEADER, params, true));
    }

    /**
     * POST 请求
     * Content-Type: application/x-www-form-urlencoded
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String postFrom(@NonNull String url, @NonNull Map<String, String> headers, Object params) {

        return execute(PostRequestBuilder.create(url, headers, params, true));
    }


    /**
     * PUT 请求
     * Content-Type: application/json
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String put(@NonNull String url, @NonNull Map<String, String> headers, Object params) {

        return execute(PostRequestBuilder.create(url, headers, params, false));
    }


    /**
     * PUT 请求
     * Content-Type: application/x-www-form-urlencoded
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String putFrom(@NonNull String url, @NonNull Map<String, String> headers, Object params) {

        return execute(PostRequestBuilder.create(url, headers, params, true));
    }

    /**
     * DELETE 请求
     * Content-Type: application/json
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String delete(@NonNull String url, @NonNull Map<String, String> headers, Object params) {

        return execute(DeleteRequestBuilder.create(url, headers, params, false));
    }


    /**
     * DELETE 请求
     * Content-Type: application/x-www-form-urlencoded
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String deleteFrom(@NonNull String url, @NonNull Map<String, String> headers, Object params) {

        return execute(DeleteRequestBuilder.create(url, headers, params, true));
    }

    /**
     * 文件上传
     * @param url       请求地址
     * @param headers   请求头部
     * @param params    请求参数
     * @return -
     */
    public static String upload(@NonNull String url,
                                @NonNull Map<String, String> headers,
                                @NonNull Map<String, File> params) {

        return execute(MultipartFormRequestBuilder.create(url, headers, params));
    }


    public static String execute(RequestBuilder requestBuilder) {
        return execute(CLIENT, requestBuilder, RESPONSE_HANDLER);
    }

    public static <T> T execute(RequestBuilder requestBuilder, ResponseHandler<T> handler) {
        return execute(CLIENT, requestBuilder, handler);
    }

    public static <T> T execute(OkHttpClient httpClient, RequestBuilder requestBuilder, ResponseHandler<T> handler) {
        Request request = requestBuilder.build();
        try {
            Response response = httpClient.newCall(request).execute();
            Optional<T> optional = handler.handle(response);

            if (optional.isPresent()) {
                return optional.get();
            }
        } catch (IOException e) {
            log.error("Http 执行请求异常", e);
        }
        return null;
    }


    /**
     * 异步执行请求
     * @param requestBuilder  请求构建
     * @param callback        回调
     */
    public static void asyncExecuted(RequestBuilder requestBuilder, Callback callback) {
        asyncExecuted(CLIENT, requestBuilder, callback);
    }

    /**
     * 异步执行请求
     * @param httpClient       Http Client
     * @param requestBuilder  请求构建
     * @param callback        回调
     */
    public static void asyncExecuted(OkHttpClient httpClient, RequestBuilder requestBuilder, Callback callback) {
        httpClient.newCall(requestBuilder.build()).enqueue(callback);
    }

}
