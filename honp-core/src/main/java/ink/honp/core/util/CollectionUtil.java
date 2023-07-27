package ink.honp.core.util;

import java.util.Collection;

/**
 * 集合工具类
 * @author jeff chen
 * @since 1.0.0
 */
public abstract class CollectionUtil {

    private CollectionUtil() {

    }

    /**
     * 判断集合是否为空
     * <pre>
     *     CollectionUtil.isEmpty(null)              --> true
     *     CollectionUtil.isEmpty(new ArrayList<>()) --> true
     * </pre>
     * @param coll 集合
     * @return {@code true} 空或null
     */
    public static boolean isEmpty(Collection<?> coll) {

        return null == coll || coll.isEmpty();
    }

    /**
     * 判断集合非空
     * @param coll -
     * @return {@code true} 集合不为空且不为 null
     */
    public static boolean isNotEmpty(Collection<?> coll) {

        return null != coll && !coll.isEmpty();
    }



}
