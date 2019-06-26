package com.okzhu.oktools.util.type;

import java.util.List;

/**
 * 通用 List 的工具集
 */
public class ListUtil {

    /**
     * @throws IllegalStateException
     */
    private ListUtil() {
        throw new IllegalStateException("ListUtil class");
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(List<?> list) {
        return (list == null) || list.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(List<?> list) {
        return (list != null) && !(list.isEmpty());
    }

}
