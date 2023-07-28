package ink.honp.core.http;

import lombok.Getter;

/**
 * http 协议数据类型标准
 * @author jeff chen
 * @since 2023/3/27 21:56
 */
@Getter
public enum HttpMediaType {

    APPLICATION_JSON("application/json"),
    MULTIPART_FORM_DATA("multipart/form-data");

    private final String type;

    HttpMediaType(String type) {
        this.type = type;
    }
}
