package com.github.okzhu.toolkit.base.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @see ArrayUtil
 */
class ArrayUtilTest {

    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }

}

/**
 * @see ArrayUtil#isEmpty(Object[])
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
 * @see ArrayUtil#isNotEmpty(Object[])
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
        Assert.assertFalse(ArrayUtil.isNotEmpty(new String[0]));
    }

    /**
     * 判断 {@link java.lang.reflect.Array} 不为空
     * 入参为 数组长度不为 0  时 返回 true
     */
    @Test
    void isNotEmptyLengthNotZero() {
        String[] arr = new String[1];
        Assert.assertTrue(ArrayUtil.isNotEmpty(arr));

        String cats[] = new String[]{"cats"};
        String dogs[] = {"dog"};

        Assert.assertTrue(ArrayUtil.isNotEmpty(cats));
        Assert.assertTrue(ArrayUtil.isNotEmpty(dogs));

    }

}

/**
 * @see ArrayUtil#newArray(Class, int)
 */
class ArrayUtilNewArrayTest {

    /**
     * 根据传入的 {@link java.lang.Class} 和长度创建数组
     */
    @Test
    void newArray() {
        String[] strs = ArrayUtil.newArray(String.class, 0);
        Assert.assertArrayEquals(strs, new String[0]);
    }
}

/**
 * @see ArrayUtil#toArray(Collection, Class)
 */
class ArrayUtilToArrayTest {

    @Test
    void toArray() {
        List<String> list = new ArrayList<String>();
        String[] arr = ArrayUtil.toArray(list, String.class);
        Assert.assertArrayEquals(arr, new String[0]);
    }

}

/**
 * @see ArrayUtil#toArray(Collection, Class)
 */
class ArrayUtilconcatTest {

    @Test
    void concat() {
        String[] strs = ArrayUtil.newArray(String.class, 0);
        strs = ArrayUtil.concat("1", strs);
        Assert.assertEquals(1, strs.length);
//        Assert.assertTrue(strs[0].equals("1"));

        strs = ArrayUtil.concat(strs, "2");
        Assert.assertEquals(2, strs.length);
//        Assert.assertTrue(strs[1].equals("2"));
    }

}