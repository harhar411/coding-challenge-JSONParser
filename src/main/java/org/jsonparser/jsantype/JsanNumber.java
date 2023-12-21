package org.jsonparser.jsantype;

import java.io.Serial;

public class JsanNumber extends Jsan {
    @Serial
    private static final long serialVersionUID = 0L;
    public JsanNumber(String rawValue) {
        super(JsanType.NUMBER, rawValue);
        this.setValue(rawValue.trim());
    }
}
