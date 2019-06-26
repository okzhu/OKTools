package com.okzhu.oktools.util.type;

/**
 * 数组工具类.
 */
public class ArrayUtil {

    /**
     * @throws IllegalStateException
     */
    private ArrayUtil() {
        throw new IllegalStateException("ArrayUtil class");
    }

    /**
     * 判断是否为空.
     */
    public static <T> boolean isEmpty(T[] arr) {
        return (arr == null) || arr.length == 0;
    }

    /**
     * 判断是否不为空.
     */
    public static <T> boolean isNotEmpty(T[] arr) {
        return (arr != null) && (arr.length != 0);
    }

}
