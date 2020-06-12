package com.github.okzhu.toolkit.base.exception;

public class OKBaseException extends RuntimeException {

    public OKBaseException() {
    }

    public OKBaseException(String message) {
        super(message);
    }


    public OKBaseException(Throwable wrapped) {
        super(wrapped);
    }

    public OKBaseException(String message, Throwable cause) {
        super(message, cause);
    }


    @Override
    public String getMessage() {
        return super.getCause().getMessage();
    }
}
