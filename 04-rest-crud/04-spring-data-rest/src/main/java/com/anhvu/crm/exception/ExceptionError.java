package com.anhvu.crm.exception;

public class ExceptionError extends RuntimeException {
    public ExceptionError(String message) {
        super(message);
    }

    public ExceptionError(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionError(Throwable cause) {
        super(cause);
    }
}
