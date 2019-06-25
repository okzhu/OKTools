package com.okzhu.oktools.validator;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class EmptyTest {


    @Test
    void isEmptyObject() {
        Object obj = null;
        Assert.assertTrue(Empty.isEmpty(obj));
    }

    @Test
    void isEmptyCollection() {
        ArrayList<Object> list = new ArrayList<Object>();
        Assert.assertTrue(Empty.isEmpty(list));
    }

    @Test
    void isEmptyMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        Assert.assertTrue(Empty.isEmpty(map));
    }

    @Test
    void isEmptyCharSequence() {
        String str = "";
        Assert.assertTrue(Empty.isEmpty(str));
    }


}
