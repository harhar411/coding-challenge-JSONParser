package org.jsonparser.jsantype;

import java.io.Serial;

public class JsanNull extends Jsan {
    @Serial
    private static final long serialVersionUID = 0L;
    public JsanNull(String rawValue) {
        super(JsanType.NULL, rawValue);
        this.setValue("null");
    }
}
