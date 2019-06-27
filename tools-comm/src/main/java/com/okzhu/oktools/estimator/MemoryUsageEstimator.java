package com.okzhu.oktools.estimator;


import com.okzhu.oktools.copyist.lucene.util.RamUsageEstimator;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

/**
 *
 */
public class MemoryUsageEstimator {

    private MemoryUsageEstimator() {
    }


    public static MemoryUsage getMemoryUseInfo() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
    }


    /**
     * 计算指定对象本身在堆空间的大小，单位字节
     *
     * @param ovj
     * @return
     */
    public static long shallowSizeOf(Object ovj) {
        return RamUsageEstimator.shallowSizeOf(ovj);
    }

    public static long sizeOfObject(Object ovj) {
        return RamUsageEstimator.sizeOfObject(ovj);
    }

}
