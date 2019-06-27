package com.okzhu.oktools.util.type;

import org.apache.commons.lang3.StringUtils;

/**
 * 通用 String 的工具集
 */
public class StringUtil {

    /**
     *
     */
    private StringUtil() {
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(final CharSequence cs) {
        return StringUtils.isEmpty(cs);
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return StringUtils.isNotEmpty(cs);
    }

}
