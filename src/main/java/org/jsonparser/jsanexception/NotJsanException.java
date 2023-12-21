package org.jsonparser.jsanexception;

import java.io.Serial;

public class NotJsanException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 0L;

    public NotJsanException() {
        super();
    }

    public NotJsanException(String message) {
        super(message);
    }

    public NotJsanException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotJsanException(Throwable cause) {
        super(cause);
    }

    protected NotJsanException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
