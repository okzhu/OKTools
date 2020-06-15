package com.github.okzhu.toolkit.base.vo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResultTest {


    Result<String> DEF_RESULT = new Result<>();
    Result<String> SUCCESS_RESULT = new Result<>();
    Result<String> ERR_RESULT = new Result<>();

    {
        SUCCESS_RESULT.setData("SUCCESS");
        ERR_RESULT.setSuccess(false);
        ERR_RESULT.setErrCode(600L);
        ERR_RESULT.setMessage("ERR");
        ERR_RESULT.setData("ERR_DATA");
    }


    @AfterAll
    void tearDown() {
        DEF_RESULT = null;
        SUCCESS_RESULT = null;
        ERR_RESULT = null;
    }

    @Test
    void isSuccess() {

        Assertions.assertEquals(Boolean.TRUE, DEF_RESULT.isSuccess());
        Assertions.assertEquals(Boolean.TRUE, SUCCESS_RESULT.isSuccess());
        Assertions.assertNotEquals(Boolean.TRUE, ERR_RESULT.isSuccess());

    }

//    @Test
//    void getErrCode() {
//    }
//
//    @Test
//    void getMessage() {
//    }
//
//    @Test
//    void getData() {
//    }
//
//    @Test
//    void setSuccess() {
//    }
//
//    @Test
//    void setErrCode() {
//    }
//
//    @Test
//    void setMessage() {
//    }
//
//    @Test
//    void setData() {
//    }
//
//
//    @Test
//    void equals() {
//    }
//
//    @Test
//    void canEqual() {
//    }

}