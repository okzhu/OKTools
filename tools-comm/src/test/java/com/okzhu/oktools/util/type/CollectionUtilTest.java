package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
class CollectionUtilTest {
    @Test
    void isTrue() {
        Assert.assertTrue(true);
    }
}

/**
 * @see com.okzhu.oktools.util.type.CollectionUtil#isEmpty(Collection)
 */
class CollectionUtilisEmptyTest {

    @Test
    void isEmptyNull() {
        Assert.assertTrue(CollectionUtil.isEmpty(null));
    }

    @Test
    void isEmpty() {
        Assert.assertTrue(CollectionUtil.isEmpty(new ArrayList<>()));
    }

    @Test
    void isEmpty1() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertFalse(CollectionUtil.isEmpty(list));
    }

}

/**
 * @see com.okzhu.oktools.util.type.CollectionUtil#isNotEmpty(Collection)
 */
class CollectionUtilisNotEmptyTest {

    @Test
    void isNotEmptyNull() {
        Assert.assertFalse(CollectionUtil.isNotEmpty(null));
    }

    @Test
    void isNotEmpty() {
        Assert.assertFalse(CollectionUtil.isNotEmpty(new ArrayList<>()));
    }

    @Test
    void isNotEmpty1() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertTrue(CollectionUtil.isNotEmpty(list));
    }
}