package com.github.okzhu.toolkit.base.type;

import java.util.Set;

/**
 * 通用 Set 的工具集
 */
public class SetUtil {


    /**
     * 不允许 new
     */
    private SetUtil() {
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(final Set set) {
        return (set == null) || set.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(final Set set) {
        return (set != null) && !(set.isEmpty());
    }

}
