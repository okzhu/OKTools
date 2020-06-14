package com.github.okzhu.toolkit.base.exception;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class OKRestExceptionTest {

    private static final String ERR_NESSAGE = "err";
    private static final Long ERR_CODE = 2000L;
    private static final Long ERR_CODE_DEF = 1000L;
    private static final String ERR_DATA = "ERR_DATA";

    private static final OKRestException OK_REST_EXCEPTION_1 = new OKRestException(ERR_NESSAGE);
    private static final OKRestException OK_REST_EXCEPTION_2 = new OKRestException(new Error(ERR_NESSAGE));
    private static final OKRestException OK_REST_EXCEPTION_3 = new OKRestException(ERR_CODE, ERR_NESSAGE);
    private static final OKRestException OK_REST_EXCEPTION_4 = new OKRestException(ERR_CODE, ERR_NESSAGE, ERR_DATA);

    @Test
    void getErrCode() {


        Assert.assertEquals(ERR_CODE_DEF, OK_REST_EXCEPTION_1.getErrCode());
        Assert.assertEquals(ERR_CODE_DEF, OK_REST_EXCEPTION_2.getErrCode());
        Assert.assertEquals(ERR_CODE, OK_REST_EXCEPTION_3.getErrCode());
        Assert.assertEquals(ERR_CODE, OK_REST_EXCEPTION_4.getErrCode());

    }

    @Test
    void getMessage() {

        Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_1.getMessage());
        Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_2.getMessage());
        Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_3.getMessage());
        Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_4.getMessage());
    }

    @Test
    void getData() {
        Assert.assertEquals(null, OK_REST_EXCEPTION_1.getData());
        Assert.assertEquals(null, OK_REST_EXCEPTION_2.getData());
        Assert.assertEquals(null, OK_REST_EXCEPTION_3.getData());
        Assert.assertEquals(ERR_DATA, OK_REST_EXCEPTION_4.getData());
    }
}