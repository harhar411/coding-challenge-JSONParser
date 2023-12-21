package org.jsonparser.jsanexception;

import java.io.Serial;

public class NotJsanKeyException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 0L;

    public NotJsanKeyException() {
        super();
    }

    public NotJsanKeyException(String message) {
        super(message);
    }

    public NotJsanKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotJsanKeyException(Throwable cause) {
        super(cause);
    }

    protected NotJsanKeyException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
