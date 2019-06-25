package com.okzhu.oktools.validator;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class EmptyTest {


    @Test
    public void isEmptyObject() {
        Object obj = null;
        Assert.assertTrue(Empty.isEmpty(obj));
    }

    @Test
    public void isEmptyCollection() {
        ArrayList<Object> list = new ArrayList<Object>();
        Assert.assertTrue(Empty.isEmpty(list));
    }

    @Test
    public void isEmptyMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        Assert.assertTrue(Empty.isEmpty(map));
    }

    @Test
    public void isEmptyCharSequence() {
        String str = "";
        Assert.assertTrue(Empty.isEmpty(str));
    }


}
