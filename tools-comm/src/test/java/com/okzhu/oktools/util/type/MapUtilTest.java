package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see com.okzhu.oktools.util.type.MapUtil
 */
class MapUtilTest {

    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }

    @Test
    void isNotEmpty() {
        Map<Object, Object> map = new HashMap<>();
        map.put("a","b");
        Assert.assertTrue(MapUtil.isNotEmpty(map));
    }
}

/**
 * @see com.okzhu.oktools.util.type.MapUtil#isEmpty(Map)
 */
class MapUtilIsEmptyTest {

    /**
     * 判断 {@link java.util.Map} 为空
     * 入参为 null 时 返回 true
     */
    @Test
    void isEmptyNull() {
        Assert.assertTrue(MapUtil.isEmpty(null));
    }

    /**
     * 判断 {@link java.util.Map} 为空
     * 入参为 数组长度为 0  时 返回 true
     */
    @Test
    void isEmptyLengthZero() {
        Assert.assertTrue(MapUtil.isEmpty(new HashMap<>()));
    }

    /**
     * 判断 {@link java.util.Map} 为空
     * 入参为 数组长度不为 0  时 返回 false
     */
    @Test
    void isEmptyLengthNotZero() {
        Map<Object, Object> map = new HashMap<>();
        map.put("a", "b");
        Assert.assertFalse(MapUtil.isEmpty(map));
    }
}

/**
 * @see com.okzhu.oktools.util.type.MapUtil#isNotEmpty(Map)
 */
class MapUtilIsNotEmptyTest {

    /**
     * 判断 {@link java.util.Map} 不为空
     * 入参为 null 时 返回 false
     */
    @Test
    void isNotEmptyNull() {
        Assert.assertFalse(MapUtil.isNotEmpty(null));
    }

    /**
     * 判断 {@link java.util.Map} 不为空
     * 入参为 数组长度为 0  时 返回 false
     */
    @Test
    void isNotEmptyLengthZero() {
        Assert.assertFalse(MapUtil.isNotEmpty(new HashMap<>()));
    }

    /**
     * 判断 {@link java.util.Map} 不为空
     * 入参为 数组长度不为 0  时 返回 true
     */
    @Test
    void isNotEmptyLengthNotZero() {
        Map<Object, Object> map = new HashMap<>();
        map.put("a", "b");
        Assert.assertTrue(MapUtil.isNotEmpty(map));
    }

}
