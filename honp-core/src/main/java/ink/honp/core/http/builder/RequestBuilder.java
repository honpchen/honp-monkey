package ink.honp.core.http.builder;

import okhttp3.Request;

/**
 * @author jeff chen
 * @since 2023-07-27 17:31
 */
public interface RequestBuilder {

    /**
     * 构建 request
     * @return Request
     */
    Request build();
}
