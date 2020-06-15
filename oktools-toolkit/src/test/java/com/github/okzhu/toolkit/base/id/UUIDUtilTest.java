package com.github.okzhu.toolkit.base.id;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


class UUIDUtilTest {


    @Test
    @SuppressFBWarnings("PRMC_POSSIBLY_REDUNDANT_METHOD_CALLS")
    void fastUuid() {
        Assert.assertNotEquals(UUIDUtil.fastUuid(), UUIDUtil.fastUuid());
    }
}