package ink.honp.core.http.builder;

import com.fasterxml.jackson.databind.JsonNode;
import ink.honp.core.http.HttpConstant;
import ink.honp.core.util.JsonUtil;
import lombok.NonNull;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author jeff chen
 * @since 1.0.0
 */
public abstract class AbstractRequestBuilder implements RequestBuilder {

    public Headers buildHeaders(@NonNull Map<String, String> headerMap) {
        Headers.Builder headerBuild = new Headers.Builder();
        if (!headerMap.isEmpty()) {
            headerMap.forEach(headerBuild::add);
        }
        return headerBuild.build();
    }

    public RequestBody buildJsonBody(Object params) {
        String bodyContent = StringUtils.EMPTY;
        if (null != params) {
            bodyContent = JsonUtil.toStr(params);
        }
        return RequestBody.create(bodyContent, MediaType.parse(HttpConstant.APPLICATION_JSON));
    }


    public RequestBody buildFormBody(Object params) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (null != params) {
            JsonNode rootNode = JsonUtil.toNode(JsonUtil.toStr(params));
            Iterator<String> fieldNames = rootNode.fieldNames();
            while (fieldNames.hasNext()) {
                String name = fieldNames.next();
                bodyBuilder.add(name, rootNode.get(name).asText());
            }
        }
        return bodyBuilder.build();
    }
}
