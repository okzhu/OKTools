package com.okzhu.oktools.util.type;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ArrayUtilTest {

    @Test
    void isNotEmpty() {
        String[] arr = new String[1];
        arr[0] = "";
        Assert.assertTrue(ArrayUtil.isNotEmpty(arr));
    }
}

class ArrayUtilisEmptyTest{
    @Test
    void isEmpty() {
        Assert.assertTrue(ArrayUtil.isEmpty(new String[0]));
    }

}