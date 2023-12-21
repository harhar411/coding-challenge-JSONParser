package org.jsonparser.jsantype;

import java.io.Serial;

public class JsanString extends Jsan {
    @Serial
    private static final long serialVersionUID = 0L;
    public JsanString(String rawValue) {
        super(JsanType.STRING, rawValue);
        this.setValue(rawValue.trim().substring(1, rawValue.length() - 1));
        this.setCharStartAt(0);
        this.setCharEndAt(rawValue.length());
    }
}
