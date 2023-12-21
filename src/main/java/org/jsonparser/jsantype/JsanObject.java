package org.jsonparser.jsantype;

import java.io.Serial;
import java.util.List;

public class JsanObject extends Jsan {
    @Serial
    private static final long serialVersionUID = 0L;
    private String key;
    private List<JsanField> fields;

    public JsanObject(String rawValue) {
        super(JsanType.OBJECT, rawValue);
        String trimmedValue = rawValue.trim();
        if(trimmedValue.charAt(0) == '{' && rawValue.charAt(trimmedValue.length() - 1) == '}')
            this.setValue(trimmedValue.substring(1, trimmedValue.length() - 1));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<JsanField> getFields() {
        return fields;
    }

    public void setFields(List<JsanField> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        String superFields = superString.substring(superString.indexOf("{") + 1);

        return "JsanObject{"
                + "key='"
                + key + '\''
                + ", fields="
                + fields
                + (superString.substring(superString.indexOf("{")).length() > 2 ? ", " + superFields : superFields);
    }
}
