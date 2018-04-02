package com.igap.application.api.exception;


public class ApiException extends RuntimeException {

    private String errorCode;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public ApiException(String msg) {
        this(null, msg);
    }

    public ApiException(String errorCode, String msg) {
        super(msg != null ? msg : errorCode);
        this.errorCode = errorCode;
        this.msg = msg;
    }
}
