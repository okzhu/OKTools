package com.okzhu.oktools.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ArrayUtilTest {

    @Test
    void newArray() {
        String[] arr = ArrayUtil.newArray(String.class, 0);
        Assert.assertEquals(arr.length, 0);
    }

    @Test
    void toArray() {
    }


}