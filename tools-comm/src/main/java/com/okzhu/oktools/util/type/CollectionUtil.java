package com.okzhu.oktools.util.type;

import java.util.Collection;

/**
 * 通用 Collection 的工具集
 */
public class CollectionUtil {

    /**
     *
     */
    private CollectionUtil() {
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(final Collection<?> collection) {
        return (collection == null) || collection.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(final Collection<?> collection) {
        return (collection != null) && !(collection.isEmpty());
    }

}
