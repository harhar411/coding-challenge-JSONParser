package org.jsonparser.jsanexception;

import java.io.Serial;

public class NotJsanStringException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 0L;

    public NotJsanStringException() {
        super();
    }

    public NotJsanStringException(String message) {
        super(message);
    }

    public NotJsanStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotJsanStringException(Throwable cause) {
        super(cause);
    }

    protected NotJsanStringException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
