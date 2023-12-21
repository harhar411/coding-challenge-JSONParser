package org.jsonparser.jsantype;

import java.io.Serial;

public class JsanKey extends JsanString {
    @Serial
    private static final long serialVersionUID = 0L;
    public JsanKey(String rawValue) {
        super(rawValue);
        this.setValue(rawValue.substring(1, rawValue.length() - 1));
    }
}
