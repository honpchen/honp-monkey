package ink.honp.core.http.builder;

import com.fasterxml.jackson.databind.JsonNode;
import ink.honp.core.util.JsonUtil;
import lombok.NonNull;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public class GetRequestBuilder extends AbstractRequestBuilder {

    private String url;
    private final Map<String, String> headers;
    private final String parameters;

    private GetRequestBuilder(String url, Map<String, String> headers, Object parameter) {
        this.url = url;
        this.headers = headers;
        this.parameters = processUrlParameters(parameter);

    }

    public static GetRequestBuilder create(@NonNull String url, @NonNull Map<String, String> headers, Object parameter) {
        return new GetRequestBuilder(url, headers, parameter);
    }


    @Override
    public Request build() {
        if (StringUtils.isNotBlank(parameters)) {
            url = url.contains("?") ? url + "&" + parameters : url + "?" + parameters;
        }

        return new Request.Builder()
               .url(url)
               .headers(buildHeaders(headers))
               .get()
               .build();
    }

    private String processUrlParameters(Object parameters) {
        if (null == parameters) {
            return StringUtils.EMPTY;
        }

        JsonNode rootNode = JsonUtil.toNode(JsonUtil.toStr(parameters));
        Iterator<String> fieldNames = rootNode.fieldNames();

        StringBuilder paramBuilder = new StringBuilder();
        while (fieldNames.hasNext()) {
            String name = fieldNames.next();
            String value = rootNode.get(name).asText();

            paramBuilder.append(URLEncoder.encode(name, StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                    .append("&");
        }

        paramBuilder.deleteCharAt(paramBuilder.length() - 1);

        return paramBuilder.toString();
    }
}
