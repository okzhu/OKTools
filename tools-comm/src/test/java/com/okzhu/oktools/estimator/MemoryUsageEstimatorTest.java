package com.okzhu.oktools.estimator;

import com.carrotsearch.sizeof.RamUsageEstimator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MemoryUsageEstimatorTest {


    @Test
    void shallowSizeOf() {
        List<Object> list = new ArrayList<Object>();
        long df = RamUsageEstimator.shallowSizeOf(list);
        System.out.println(df);//24
    }

    @Test
    void sizeOfObject() {
        List<Object> list = new ArrayList<Object>();
        long df = RamUsageEstimator.sizeOf(list);
        System.out.println(df);//56
    }

}