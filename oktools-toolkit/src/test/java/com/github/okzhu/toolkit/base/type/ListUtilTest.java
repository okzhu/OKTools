package com.github.okzhu.toolkit.base.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see ListUtil
 */
class ListUtilTest {

    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }

}

/**
 * @see ListUtil#isEmpty(java.util.List)
 */
class ListUtilIsEmptyTest {
    /**
     * 判断 {@link java.util.List} 为空
     * 入参为 null 时 返回 true
     */
    @Test
    void isEmptyNull() {
        Assert.assertTrue(ListUtil.isEmpty(null));
    }

    /**
     * 判断 {@link java.util.List} 为空
     * 入参为 数组长度为 0  时 返回 true
     */
    @Test
    void isEmptyLengthZero() {
        Assert.assertTrue(ListUtil.isEmpty(new ArrayList<>()));
    }

    /**
     * 判断 {@link java.util.List} 为空
     * 入参为 数组长度不为 0  时 返回 false
     */
    @Test
    void isEmptyLengthNotZero() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertFalse(ListUtil.isEmpty(list));
    }
}

/**
 * @see ListUtil#isNotEmpty(List)
 */
class ListUtilisNotEmptyTest {

    /**
     * 判断 {@link java.util.List} 不为空
     * 入参为 null 时 返回 false
     */
    @Test
    void isNotEmptyNull() {
        Assert.assertFalse(ListUtil.isNotEmpty(null));
    }

    /**
     * 判断 {@link java.util.List} 不为空
     * 入参为 数组长度为 0  时 返回 false
     */
    @Test
    void isNotEmptyLengthZero() {
        Assert.assertFalse(ListUtil.isNotEmpty(new ArrayList<>()));
    }

    /**
     * 判断 {@link java.util.List} 不为空
     * 入参为 数组长度不为 0  时 返回 true
     */
    @Test
    void isNotEmptyLengthNotZero() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertTrue(ListUtil.isNotEmpty(list));
    }

}