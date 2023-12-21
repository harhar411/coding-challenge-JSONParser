package org.jsonparser.jsantype;

import java.io.Serial;

public class JsanBoolean extends Jsan{
    @Serial
    private static final long serialVersionUID = 0L;
    public JsanBoolean(String rawValue) {
        super(JsanType.BOOLEAN, rawValue);
        this.setValue(rawValue.trim().toUpperCase() == "TRUE" ? "true" : "false");
    }
}
