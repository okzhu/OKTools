package com.okzhu.oktools.util.type;

/**
 * 通用 Array 的工具集
 */
public class ArrayUtil {

    /**
     *
     */
    private ArrayUtil() {
    }

    /**
     * 判断是否为空.
     */
    public static <T> boolean isEmpty(final T[] arr) {
        return (arr == null) || arr.length == 0;
    }

    /**
     * 判断是否不为空.
     */
    public static <T> boolean isNotEmpty(final T[] arr) {
        return (arr != null) && (arr.length != 0);
    }

}
