package ink.honp.core.http;

import lombok.Getter;
import lombok.ToString;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
@ToString
public enum HttpMethod {

    GET("get"),
    POST("post"),
    PUT("put"),
    PATCH("patch"),
    DELETE("delete");

    private final String code;

    HttpMethod(String code) {
        this.code = code;
    }
}
