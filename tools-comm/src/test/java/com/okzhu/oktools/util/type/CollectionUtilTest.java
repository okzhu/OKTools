package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CollectionUtilTest {


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

    @Test
    void isNotEmpty() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertTrue(CollectionUtil.isNotEmpty(list));
    }

}