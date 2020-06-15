package com.github.okzhu.toolkit.base.exception;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OKBaseExceptionTest {

    @Test
    void getMessage() {
        String errNessage = "err";
        Assertions.assertAll("getMessage",
                () -> Assert.assertEquals(errNessage, new OKBaseException(errNessage).getMessage()),
                () -> Assert.assertEquals(errNessage, new OKBaseException(new Error(errNessage)).getMessage()),
                () -> Assert.assertEquals(errNessage, new OKBaseException(errNessage, new Error(errNessage)).getMessage())
        );
    }


}