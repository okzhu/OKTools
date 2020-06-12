package com.github.okzhu.toolkit.base.type;

import com.google.common.collect.ObjectArrays;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * 通用 Array 的工具集
 */
public class ArrayUtil {

    /**
     * 不允许 new
     */
    private ArrayUtil() {
    }

    /**
     * 判断是否为空
     */
    public static <T> boolean isEmpty(final T[] arr) {
        return (arr == null) || arr.length == 0;
    }

    /**
     * 判断是否不为空
     */
    public static <T> boolean isNotEmpty(final T[] arr) {
        return (arr != null) && (arr.length != 0);
    }

    /**
     * 创建数组
     */
    public static <T> T[] newArray(Class<T> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }

    /**
     * 从collection转为Array, 以 list.toArray(new String[0]); 最快 不需要创建list.size()的数组.
     * <p>
     * 本函数等价于list.toArray(new String[0]); 用户也可以直接用后者.
     * <p>
     * https://shipilev.net/blog/2016/arrays-wisdom-ancients/
     */
    public static <T> T[] toArray(Collection<T> col, Class<T> type) {
        return col.toArray((T[]) Array.newInstance(type, 0));
    }

    /**
     * 添加元素到数组头.
     */
    public static <T> T[] concat(T element, T[] array) {
        return ObjectArrays.concat(element, array);
    }

    /**
     * 添加元素到数组末尾.
     */
    public static <T> T[] concat(T[] array, T element) {
        return ObjectArrays.concat(array, element);
    }


}
