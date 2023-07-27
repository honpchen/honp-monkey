package ink.honp.core.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
@ToString
public enum DeleteStatus {

    NO(0),
    YES(1);

    private final Integer code;

    DeleteStatus(Integer code) {
        this.code = code;
    }
}
