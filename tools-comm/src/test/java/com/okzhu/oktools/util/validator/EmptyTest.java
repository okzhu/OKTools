package com.okzhu.oktools.util.validator;


import com.carrotsearch.sizeof.RamUsageEstimator;
import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void testssss() {
        List<Object> list = new ArrayList<Object>();
        long df = RamUsageEstimator.shallowSizeOf(list);
        System.out.println(df);

        Assert.assertTrue(true);

    }


}
