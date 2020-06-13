package com.github.okzhu.toolkit.base.type;

import org.apache.commons.lang3.EnumUtils;

import java.util.Set;

public class EnumUtil {

    private EnumUtil() {
    }

    /**
     * 将若干个枚举值转换为long(按bits 1,2,4,8...的方式叠加)，用于使用long保存多个选项的情况.
     */
    public static <E extends Enum<E>> long generateBits(final Class<E> enumClass, final Iterable<? extends E> values) {
        return EnumUtils.generateBitVector(enumClass, values);
    }

    /**
     * 将若干个枚举值转换为long(按bits 1,2,4,8...的方式叠加)，用于使用long保存多个选项的情况.
     */
    public static <E extends Enum<E>> long generateBits(final Class<E> enumClass, final E... values) {
        return EnumUtils.generateBitVector(enumClass, values);
    }

    /**
     * long重新解析为若干个枚举值，用于使用long保存多个选项的情况.
     */
    public static <E extends Enum<E>> Set<E> processBits(final Class<E> enumClass, final long value) {
        return EnumUtils.processBitVector(enumClass, value);
    }

    /**
     * Enum转换为String
     */
    public static String toString(Enum<?> e) {
        return e.name();
    }

    /**
     * String转换为Enum
     */
    public static <T extends Enum<T>> T fromString(Class<T> enumClass, String value) {
        return Enum.valueOf(enumClass, value);
    }
}
