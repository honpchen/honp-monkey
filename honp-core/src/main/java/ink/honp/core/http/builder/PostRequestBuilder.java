package ink.honp.core.http.builder;

import lombok.NonNull;
import okhttp3.Request;

import java.util.Map;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class PostRequestBuilder extends AbstractRequestBuilder {

    private final String url;
    private final Map<String, String> headers;
    private final Object params;
    private final boolean isForm;

    private PostRequestBuilder(String url, Map<String, String> headers, Object params, boolean isForm) {
        this.url = url;
        this.headers = headers;
        this.params = params;
        this.isForm = isForm;
    }
    
    public static PostRequestBuilder create(@NonNull String url, @NonNull Map<String, String> headers, Object params, Boolean isForm) {
        return new PostRequestBuilder(url, headers, params, isForm);
    }

    @Override
    public Request build() {
        return new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(isForm ? buildFormBody(params) : buildJsonBody(params))
                .build();
    }
}
