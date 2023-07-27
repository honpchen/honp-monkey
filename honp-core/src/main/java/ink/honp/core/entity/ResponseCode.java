package ink.honp.core.entity;

/**
 * @author jeff chen
 * @since 2023-07-27 09:52
 */
public interface ResponseCode {

    /**
     * 返回状态码
     * @return code
     */
    String getCode();

    /**
     * 提示信息
     * @return message
     */
    String getMessage();
}
