package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapUtilTest {

    @Test
    void isEmpty() {
        Map<Object, Object> map = new HashMap<>();
        Assert.assertTrue(MapUtil.isEmpty(map));
    }

    @Test
    void isNotEmpty() {
        Map<Object, Object> map = new HashMap<>();
        map.put("a","b");
        Assert.assertTrue(MapUtil.isNotEmpty(map));
    }
}