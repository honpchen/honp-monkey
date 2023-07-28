package ink.honp.core.http.builder;

import ink.honp.core.http.HttpConstant;
import lombok.NonNull;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.util.Map;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class MultipartFormRequestBuilder extends AbstractRequestBuilder {

    private final String url;
    private final Map<String, String> headers;
    private final Map<String, File> params;

    private MultipartFormRequestBuilder(String url, Map<String, String> headers, Map<String, File> params) {
        this.url = url;
        this.headers = headers;
        this.params = params;
    }

    public static MultipartFormRequestBuilder create(@NonNull String url,
                                                     @NonNull Map<String, String> headers,
                                                     @NonNull Map<String, File> params) {

        return new MultipartFormRequestBuilder(url, headers, params);
    }

    @Override
    public Request build() {

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);

        params.forEach((name, file) -> bodyBuilder.addFormDataPart(name, file.getName(),
                RequestBody.create(file, MediaType.parse(HttpConstant.MULTIPART_FORM_DATA))));

        return new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(bodyBuilder.build())
                .build();
    }
}
