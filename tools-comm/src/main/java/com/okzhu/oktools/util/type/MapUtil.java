package com.okzhu.oktools.util.type;

import java.util.Map;

/**
 * 通用 Map 的工具集
 */
public class MapUtil {

    /**
     *
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
