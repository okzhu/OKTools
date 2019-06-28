package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @see com.okzhu.oktools.util.type.SetUtil
 */
class SetUtilTest {

    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }

    @Test
    void isNotEmpty() {
        Set<String> set = new HashSet<String>();
        set.add("");
        Assert.assertTrue(SetUtil.isNotEmpty(set));
    }
}

/**
 * @see com.okzhu.oktools.util.type.SetUtil#isEmpty(Set)
 */
class SetUtilIsEmptyTest {
    /**
     * 判断 {@link java.util.Set} 为空
     * 入参为 null 时 返回 true
     */
    @Test
    void isEmptyNull() {
        Assert.assertTrue(SetUtil.isEmpty(null));
    }

    /**
     * 判断 {@link java.util.Set} 为空
     * 入参为 数组长度为 0  时 返回 true
     */
    @Test
    void isEmptyLengthZero() {
        Assert.assertTrue(SetUtil.isEmpty(new HashSet()));
    }

    /**
     * 判断 {@link java.util.Set} 为空
     * 入参为 数组长度不为 0  时 返回 false
     */
    @Test
    void isEmptyLengthNotZero() {
        Set<String> set = new HashSet<String>();
        set.add("");
        Assert.assertFalse(SetUtil.isEmpty(set));
    }

}

/**
 * @see com.okzhu.oktools.util.type.SetUtil#isNotEmpty(Set)
 */
class SetUtilIsNotEmptyTest {
    /**
     * 判断 {@link java.util.Set} 不为空
     * 入参为 null 时 返回 false
     */
    @Test
    void isNotEmptyNull() {
        Assert.assertFalse(SetUtil.isNotEmpty(null));
    }

    /**
     * 判断 {@link java.util.Set} 不为空
     * 入参为 数组长度为 0  时 返回 false
     */
    @Test
    void isNotEmptyLengthZero() {
        Assert.assertFalse(SetUtil.isNotEmpty(new HashSet<>()));
    }

    /**
     * 判断 {@link java.util.Set} 不为空
     * 入参为 数组长度不为 0  时 返回 true
     */
    @Test
    void isNotEmptyLengthNotZero() {
        Set<String> set = new HashSet<String>();
        set.add("");
        Assert.assertTrue(SetUtil.isNotEmpty(set));
    }
}
