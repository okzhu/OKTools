package com.github.okzhu.toolkit.base.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @see CollectionUtil
 */
class CollectionUtilTest {
    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }
}

/**
 * @see CollectionUtil#isEmpty(Collection)
 */
class CollectionUtilIsEmptyTest {

    /**
     * 判断 {@link java.util.Collection} 为空
     * 入参为 null 时 返回 true
     */
    @Test
    void isEmptyNull() {
        Assert.assertTrue(CollectionUtil.isEmpty(null));
    }

    /**
     * 判断 {@link java.util.Collection} 为空
     * 入参为 数组长度为 0  时 返回 true
     */
    @Test
    void isEmptyLengthZero() {
        Assert.assertTrue(CollectionUtil.isEmpty(new ArrayList<>()));
    }

    /**
     * 判断 {@link java.util.Collection} 为空
     * 入参为 数组长度不为 0  时 返回 false
     */
    @Test
    void isEmptyLengthNotZero() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertFalse(CollectionUtil.isEmpty(list));
    }

}

/**
 * @see CollectionUtil#isNotEmpty(Collection)
 */
class CollectionUtilIsNotEmptyTest {

    /**
     * 判断 {@link java.util.Collection} 不为空
     * 入参为 null 时 返回 false
     */
    @Test
    void isNotEmptyNull() {
        Assert.assertFalse(CollectionUtil.isNotEmpty(null));
    }

    /**
     * 判断 {@link java.util.Collection} 不为空
     * 入参为 数组长度为 0  时 返回 false
     */
    @Test
    void isNotEmptyLengthZero() {
        Assert.assertFalse(CollectionUtil.isNotEmpty(new ArrayList<>()));
    }

    /**
     * 判断 {@link java.util.Collection} 不为空
     * 入参为 数组长度不为 0  时 返回 true
     */
    @Test
    void isNotEmptyLengthNotZero() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertTrue(CollectionUtil.isNotEmpty(list));
    }
}