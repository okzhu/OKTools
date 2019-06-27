package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void isEmpty() {
        String str= "";
        Assert.assertTrue(StringUtil.isEmpty(str));
    }

    @Test
    void isNotEmpty() {
        String str= "3";
        Assert.assertTrue(StringUtil.isNotEmpty(str));
    }
}