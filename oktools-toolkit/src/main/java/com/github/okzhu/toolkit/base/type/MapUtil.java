package com.github.okzhu.toolkit.base.type;

import java.util.Map;

/**
 * 通用 Map 的工具集
 * @author Administrator
 */
public class MapUtil {

    /**
     * 不允许 new
     */
    private MapUtil() {
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return (map == null) || map.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return (map != null) && !map.isEmpty();
    }


}
