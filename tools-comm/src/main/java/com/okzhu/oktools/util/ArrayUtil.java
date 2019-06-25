package com.okzhu.oktools.util;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 *  数组工具类.
 */
public class ArrayUtil {

    /**
     * 传入类型与大小创建数组.
     * @param type
     * @param length
     * @param <T>
     * @return
     */
    public static <T> T[] newArray(Class<T> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }


    /**
     * 从collection转为Array, 以 list.toArray(new String[0]);
     * @param col
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(Collection<T> col, Class<T> type) {
        return col.toArray((T[]) Array.newInstance(type, 0));
    }

}
