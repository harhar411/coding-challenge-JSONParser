package org.jsonparser.jsantype;

import java.io.Serial;

public class JsanArray extends Jsan {
    @Serial
    private static final long serialVersionUID = 0L;
    public JsanArray(String rawValue) {
        super(JsanType.ARRAY, rawValue);
        String trimmedValue = rawValue.trim();
        if(trimmedValue.charAt(0) == '[' && rawValue.charAt(trimmedValue.length() - 1) == ']')
            this.setValue(trimmedValue.substring(1, trimmedValue.length() - 1));
    }

}
