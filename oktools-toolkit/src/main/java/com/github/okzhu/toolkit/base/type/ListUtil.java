package com.github.okzhu.toolkit.base.type;

import java.util.Collections;
import java.util.List;

/**
 * 通用 List 的工具集
 */
public class ListUtil {

    /**
     * 不允许 new
     */
    private ListUtil() {
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(final List<?> list) {
        return (list == null) || list.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(final List<?> list) {
        return (list != null) && !(list.isEmpty());
    }

    /**
     * 返回一个空的结构特殊的List，节约空间.
     * <p>
     * 注意返回的List不可写, 写入会抛出UnsupportedOperationException.
     *
     * @see java.util.Collections#emptyList()
     */
    public static final <T> List<T> emptyList() {
        return Collections.emptyList();
    }

}
