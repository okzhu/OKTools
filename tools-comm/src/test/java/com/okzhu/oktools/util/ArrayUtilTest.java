package com.okzhu.oktools.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ArrayUtilTest {

    @Test
    public void newArray() {
        String[] arr = ArrayUtil.newArray(String.class, 0);
        Assert.assertEquals(arr.length, 0);
    }

    @Test
    public void toArray() {
    }


}