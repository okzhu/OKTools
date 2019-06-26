package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilTest {

    @Test
    void isEmpty() {
        Assert.assertTrue(CollectionUtil.isEmpty(new ArrayList<>()));
    }

    @Test
    void isNotEmpty() {
        List<String> list = new ArrayList<String>();
        list.add("");
        Assert.assertTrue(CollectionUtil.isNotEmpty(list));
    }
}