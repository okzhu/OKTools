package com.github.okzhu.toolkit.base.type;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.BooleanUtils;

/**
 * @author Administrator
 */
public class BooleanUtil {

    private BooleanUtil() {
    }

    /**
     * 使用标准JDK，只分析是否忽略大小写的"true", str为空时返回false
     */
    public static boolean toBoolean(String str) {
        return Boolean.parseBoolean(str);
    }

    /**
     * 使用标准JDK，只分析是否忽略大小写的"true", str为空时返回null
     */
    @SuppressFBWarnings("NP_BOOLEAN_RETURN_NULL")
    public static Boolean toBooleanObject(String str) {
        return str != null ? Boolean.valueOf(str) : null;
    }

    /**
     * 使用标准JDK，只分析是否忽略大小写的"true", str为空时返回defaultValue
     */
    public static Boolean toBooleanObject(String str, Boolean defaultValue) {
        return str != null ? Boolean.valueOf(str) : defaultValue;
    }

    /**
     * 支持true/false, on/off, y/n, yes/no的转换, str为空或无法分析时返回null
     */
    public static Boolean parseGeneralString(String str) {
        return BooleanUtils.toBooleanObject(str);
    }

    /**
     * 支持true/false,on/off, y/n, yes/no的转换, str为空或无法分析时返回defaultValue
     */
    public static Boolean parseGeneralString(String str, Boolean defaultValue) {
        return BooleanUtils.toBooleanDefaultIfNull(BooleanUtils.toBooleanObject(str), defaultValue);
    }

    /**
     * 取反
     */
    public static boolean negate(final boolean bool) {
        return !bool;
    }

    /**
     * 取反
     */
    public static Boolean negate(final Boolean bool) {
        return BooleanUtils.negate(bool);
    }

    /**
     * 多个值的and
     */
    public static boolean and(final boolean... array) {
        return BooleanUtils.and(array);
    }

    /**
     * 多个值的or
     */
    public static boolean or(final boolean... array) {
        return BooleanUtils.or(array);
    }
}
