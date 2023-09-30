package com.solv.inventory.exceptions;

public class BadArgumentException extends Exception {
    public BadArgumentException() {
    }

    public BadArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadArgumentException(Throwable cause) {
        super(cause);
    }

    public BadArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BadArgumentException(String message) {
        super(message);
    }
}
