package com.github.okzhu.toolkit.base.exception;

/**
 * @author Administrator
 */
@SuppressWarnings("all")
public class OKBaseException extends RuntimeException {

    private static final long serialVersionUID = -600420843373992963L;

    private OKBaseException() {
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
        return (super.getCause() == null ? super.getMessage() : super.getCause().getMessage());
    }

}
