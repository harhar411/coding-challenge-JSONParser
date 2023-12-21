package org.jsonparser.jsanexception;

import java.io.Serial;

public class NotJsanFieldException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 0L;

    public NotJsanFieldException() {
        super();
    }

    public NotJsanFieldException(String message) {
        super(message);
    }

    public NotJsanFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotJsanFieldException(Throwable cause) {
        super(cause);
    }

    protected NotJsanFieldException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
