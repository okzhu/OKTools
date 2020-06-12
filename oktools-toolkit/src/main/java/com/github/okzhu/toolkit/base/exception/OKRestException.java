package com.github.okzhu.toolkit.base.exception;

public class OKRestException extends OKBaseException {

    private Long errCode = 1000L;
    private String message = "Service unavailable,please contact with administrator";
    private Object data = null;

    private OKRestException() {
    }

    public OKRestException(String message) {
        super(message);
        this.message = message;
    }

    public OKRestException(Throwable e) {
        this(e.getMessage());
    }


    public OKRestException(Long errCode, String message) {
        this(message);
        this.errCode = errCode;

    }

    public OKRestException(Long errCode, String message, Object data) {
        this(message);
        this.errCode = errCode;
        this.data = data;
    }

    public Long getErrCode() {
        return errCode;
    }

    public void setErrCode(Long errCode) {
        this.errCode = errCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
