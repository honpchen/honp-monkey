package ink.honp.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * JSON 工具类，提供简单对象和字符串互转
 *
 * @author jeff chen
 * @since 1.0.0
 */
@Slf4j
public abstract class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 取消默认转换timestamps形式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        // 忽略空Bean转json的错误
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        // 忽略在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        // 支持 java.time.LocalDateTime 类型
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    private JsonUtil(){

    }

    /**
     * 字符串转对象
     * @param jsonText json 字符串
     * @param clz      对象类型
     * @return 转换失败返回 null
     * @param <T> -
     */
    public static <T> T toBean(final String jsonText, @NonNull Class<T> clz) {

        if (StringUtils.isNotBlank(jsonText)) {
            try {
                return OBJECT_MAPPER.readValue(jsonText, clz);
            } catch (JsonProcessingException e) {
                log.warn("json 解析失败: {}", jsonText, e);
            }
        }
        return null;
    }

    /**
     * 对象转字符串
     * @param obj 对象
     * @return json 字符串
     */
    public static String toStr(@NonNull Object obj) {

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("对象转字符串错误");
        }
        return null;
    }

    /**
     * 字符串转对象，适用含泛型类型的对象转换
     * <p>e.g. toBean("[1,2,3,4]", List.class, Integer.class)</p>
     * @param jsonText   json 字符串
     * @param parametrized    基类
     * @param parameterClz 泛型元素类
     * @return 转换失败返回 Optional.empty()
     * @param <T> -
     * @param <E> -
     */
    public static <T,E> T toBean(final String jsonText, Class<T> parametrized, Class<E> parameterClz) {

        if (StringUtils.isNotBlank(jsonText)) {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClz);
            try {
                return OBJECT_MAPPER.readValue(jsonText, javaType);
            } catch (JsonProcessingException e) {
                log.warn("json 解析失败: {}", jsonText, e);
            }
        }

        return null;
    }

    /**
     * 字符串转 List
     * @param jsonText      json 字符串
     * @param elementClz    元素类型
     * @return 失败返回空 List
     * @param <T> -
     */
    public static <T> List<T> toList(final String jsonText, Class<T> elementClz) {
        if (StringUtils.isNotBlank(jsonText)) {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, elementClz);
            try {
                return OBJECT_MAPPER.readValue(jsonText, javaType);
            } catch (JsonProcessingException e) {
                log.warn("json 转 List 失败: {}", jsonText, e);
            }
        }
        return Collections.emptyList();
    }

    /**
     * 字符串转 {@link JsonNode}
     * @param jsonText json 字符串
     * @return -
     */
    public static JsonNode toNode(@NonNull String jsonText) {
        try {
            return OBJECT_MAPPER.readTree(jsonText);
        } catch (JsonProcessingException e) {
            log.warn("json 转 node 失败: {}", jsonText, e);
            return null;
        }
    }

}
