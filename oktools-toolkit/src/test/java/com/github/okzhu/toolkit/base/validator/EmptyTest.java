package com.github.okzhu.toolkit.base.validator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmptyTest {

    @Test
    public void isEmptyCollection() {
        List<Object> list = new ArrayList<Object>();
        Assert.assertTrue(Empty.isEmpty(list));
    }

    @Test
    public void isEmptyCharSequence() {
        String str = "";
        Assert.assertTrue(Empty.isEmpty(str));
    }

}
