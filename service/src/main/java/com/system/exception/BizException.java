package com.system.exception;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1095242212086237834L;

    protected Object errorCode;
    protected Object[] args;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BizException(String message, Object errorCode, Object[] args) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }

    public BizException(String message, Throwable cause, Object errorCode, Object[] args) {
        super(message, cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public BizException(Throwable cause, Object errorCode, Object[] args) {
        super(cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
