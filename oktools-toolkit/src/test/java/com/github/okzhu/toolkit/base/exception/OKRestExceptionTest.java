package com.github.okzhu.toolkit.base.exception;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class OKRestExceptionTest {

    private String ERR_NESSAGE = "err";
    private Long ERR_CODE = 2000L;
    private Long ERR_CODE_DEF = 1000L;
    private String ERR_DATA = "ERR_DATA";

    private OKRestException OK_REST_EXCEPTION_1 = new OKRestException(ERR_NESSAGE);
    private OKRestException OK_REST_EXCEPTION_2 = new OKRestException(new Error(ERR_NESSAGE));
    private OKRestException OK_REST_EXCEPTION_3 = new OKRestException(ERR_CODE, ERR_NESSAGE);
    private OKRestException OK_REST_EXCEPTION_4 = new OKRestException(ERR_CODE, ERR_NESSAGE, ERR_DATA);


    @Test
    void getErrCode() {

        Assertions.assertAll("getErrCode",
                () -> Assertions.assertEquals(ERR_CODE_DEF, OK_REST_EXCEPTION_1.getErrCode()),
                () -> Assertions.assertEquals(ERR_CODE_DEF, OK_REST_EXCEPTION_2.getErrCode()),
                () -> Assertions.assertEquals(ERR_CODE, OK_REST_EXCEPTION_3.getErrCode()),
                () -> Assertions.assertEquals(ERR_CODE, OK_REST_EXCEPTION_4.getErrCode())
        );


        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        Assertions.assertEquals("a message", exception.getMessage());

    }

    @Test
    void getMessage() {
        Assertions.assertAll("getMessage",
                () -> Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_1.getMessage()),
                () -> Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_2.getMessage()),
                () -> Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_3.getMessage()),
                () -> Assert.assertEquals(ERR_NESSAGE, OK_REST_EXCEPTION_4.getMessage())
        );
    }

    @Test
    void getData() {
        Assertions.assertAll("getData",
                () -> Assert.assertEquals(null, OK_REST_EXCEPTION_1.getData()),
                () -> Assert.assertEquals(null, OK_REST_EXCEPTION_2.getData()),
                () -> Assert.assertEquals(null, OK_REST_EXCEPTION_3.getData()),
                () -> Assert.assertEquals(ERR_DATA, OK_REST_EXCEPTION_4.getData())
        );
    }
}