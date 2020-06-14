package com.github.okzhu.toolkit.base.id;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class UUIDUtilTest {

    @Test
    void fastUuid() {
        Assert.assertNotEquals(UUIDUtil.fastUuid(), UUIDUtil.fastUuid());
    }
}