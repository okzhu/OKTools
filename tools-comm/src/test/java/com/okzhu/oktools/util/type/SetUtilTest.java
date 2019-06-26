package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetUtilTest {

    @Test
    void isEmpty() {
        Set<String> set = new HashSet<String>();
        Assert.assertTrue(SetUtil.isEmpty(set));
    }

    @Test
    void isNotEmpty() {
        Set<String> set = new HashSet<String>();
        set.add("");
        Assert.assertTrue(SetUtil.isNotEmpty(set));
    }
}