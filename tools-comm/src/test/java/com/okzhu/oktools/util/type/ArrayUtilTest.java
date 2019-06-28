package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @see com.okzhu.oktools.util.type.ArrayUtil
 */
class ArrayUtilTest {

    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }

}

/**
 * @see com.okzhu.oktools.util.type.ArrayUtil#isEmpty(Object[])
 */
class ArrayUtilIsEmptyTest {

    /**
     * 判断 {@link java.lang.reflect.Array} 为空
     * 入参为 null 时 返回 true
     */
    @Test
    void isEmptyNull() {
        Assert.assertTrue(ArrayUtil.isEmpty(null));
    }

    /**
     * 判断 {@link java.lang.reflect.Array} 为空
     * 入参为 数组长度为 0  时 返回 true
     */
    @Test
    void isEmptyLengthZero() {
        Assert.assertTrue(ArrayUtil.isEmpty(new String[0]));
    }

    /**
     * 判断 {@link java.lang.reflect.Array} 为空
     * 入参为 数组长度不为 0  时 返回 false
     */
    @Test
    void isEmptyLengthNotZero() {

        String cats[] = new String[]{"cats"};
        String dogs[] = {"dog"};

        Assert.assertFalse(ArrayUtil.isEmpty(cats));

        Assert.assertFalse(ArrayUtil.isEmpty(dogs));
    }

}

/**
 * @see com.okzhu.oktools.util.type.ArrayUtil#isNotEmpty(Object[])
 */
class ArrayUtilIsNotEmptyTest {

    /**
     * 判断 {@link java.lang.reflect.Array} 不为空
     * 入参为 null 时 返回 false
     */
    @Test
    void isNotEmptyNull() {
        Assert.assertFalse(ArrayUtil.isNotEmpty(null));
    }

    /**
     * 判断 {@link java.lang.reflect.Array} 不为空
     * 入参为 数组长度为 0  时 返回 false
     */
    @Test
    void isNotEmptyLengthZero() {
        String[] arr = new String[1];

        Assert.assertTrue(ArrayUtil.isNotEmpty(arr));
    }

    /**
     * 判断 {@link java.lang.reflect.Array} 不为空
     * 入参为 数组长度不为 0  时 返回 true
     */
    @Test
    void isNotEmptyLengthNotZero() {
        String[] arr = new String[1];
        arr[0] = "";
        Assert.assertTrue(ArrayUtil.isNotEmpty(arr));
    }


}