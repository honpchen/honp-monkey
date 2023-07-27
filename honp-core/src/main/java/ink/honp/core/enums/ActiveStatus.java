package ink.honp.core.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author jeff chen
 * @since 2023-07-26 18:10
 */
@Getter
@ToString
public enum ActiveStatus {

    NO(0, "已禁用"),
    YES(1, "已激活");

    private final Integer code;
    private final String desc;

    ActiveStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
