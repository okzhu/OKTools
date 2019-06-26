package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ListUtilTest {

    @Test
    void isEmpty() {
        Assert.assertTrue(ListUtil.isEmpty(new ArrayList<>()));
    }

    @Test
    void isNotEmpty() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertTrue(ListUtil.isNotEmpty(list));
    }
}