package com.okzhu.oktools.util.type;

import java.util.List;

/**
 * 通用 List 的工具集
 */
public class ListUtil {

    /**
     *
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

}
