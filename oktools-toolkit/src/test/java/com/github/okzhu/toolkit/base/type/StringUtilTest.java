package com.github.okzhu.toolkit.base.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @see StringUtil
 */
class StringUtilTest {

    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }

    @Test
    void isNotEmpty() {
        String str = "3";
        Assert.assertTrue(StringUtil.isNotEmpty(str));
    }
}

/**
 * @see StringUtil#isEmpty(CharSequence)
 */
class StringUtilIsEmptyTest {
    /**
     * 判断 {@link java.lang.CharSequence} 为空
     * 入参为 null 时 返回 true
     */
    @Test
    void isEmptyNull() {
        Assert.assertTrue(StringUtil.isEmpty(null));
    }

    /**
     * 判断 {@link java.lang.CharSequence} 为空
     * 入参为 数组长度为 0  时 返回 true
     */
    @Test
    void isEmptyLengthZero() {
        Assert.assertTrue(StringUtil.isEmpty(""));
    }

    /**
     * 判断 {@link java.lang.CharSequence} 为空
     * 入参为 数组长度不为 0  时 返回 false
     */
    @Test
    void isEmptyLengthNotZero() {
        Assert.assertFalse(StringUtil.isEmpty("f"));
    }
}

/**
 * @see StringUtil#isNotEmpty(CharSequence)
 */
class StringUtilIsNotEmptyTest {

    /**
     * 判断 {@link java.lang.CharSequence} 不为空
     * 入参为 null 时 返回 false
     */
    @Test
    void isNotEmptyNull() {
        Assert.assertFalse(StringUtil.isNotEmpty(null));
    }

    /**
     * 判断 {@link java.lang.CharSequence} 不为空
     * 入参为 数组长度为 0  时 返回 false
     */
    @Test
    void isNotEmptyLengthZero() {
        Assert.assertFalse(StringUtil.isNotEmpty(""));
    }

    /**
     * 判断 {@link java.lang.CharSequence} 不为空
     * 入参为 数组长度不为 0  时 返回 true
     */
    @Test
    void isNotEmptyLengthNotZero() {
        Assert.assertTrue(StringUtil.isNotEmpty("d"));
    }

}