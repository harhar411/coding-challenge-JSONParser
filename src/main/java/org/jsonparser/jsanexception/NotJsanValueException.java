package org.jsonparser.jsanexception;

import java.io.Serial;

public class NotJsanValueException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 0L;

    public NotJsanValueException() {
        super();
    }

    public NotJsanValueException(String message) {
        super(message);
    }

    public NotJsanValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotJsanValueException(Throwable cause) {
        super(cause);
    }

    protected NotJsanValueException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
