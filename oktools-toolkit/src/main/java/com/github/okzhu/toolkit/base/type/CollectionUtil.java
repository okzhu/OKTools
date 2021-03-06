package com.github.okzhu.toolkit.base.type;

import java.util.Collection;

/**
 * 通用 Collection 的工具集
 * @author Administrator
 */
public class CollectionUtil {

    /**
     * 不允许 new
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
