package org.jsonparser.jsantype;

import java.io.Serial;

public class JsanField extends Jsan {
    @Serial
    private static final long serialVersionUID = 0L;
    private JsanKey key;
    private Jsan jsanValue;

    public JsanField(String rawValue) {
        super(JsanType.FIELD, rawValue);
        String trimmedValue = rawValue.trim();
        this.setValue(trimmedValue.endsWith(",") ? trimmedValue.substring(0, trimmedValue.length() - 1) : trimmedValue);
    }

    public JsanKey getKey() {
        return key;
    }

    public void setKey(JsanKey key) {
        this.key = key;
    }

    public Jsan getJsanValue() {
        return jsanValue;
    }

    public void setJsanValue(Jsan jsanValue) {
        this.jsanValue = jsanValue;
    }
}
