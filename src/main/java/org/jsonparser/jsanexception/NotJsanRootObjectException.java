package org.jsonparser.jsanexception;

import java.io.Serial;

public class NotJsanRootObjectException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 0L;

    public NotJsanRootObjectException() {
        super();
    }

    public NotJsanRootObjectException(String message) {
        super(message);
    }

    public NotJsanRootObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotJsanRootObjectException(Throwable cause) {
        super(cause);
    }

    protected NotJsanRootObjectException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
