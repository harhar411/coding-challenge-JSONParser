package org.jsonparser.jsantype;

import java.io.Serial;

public class Jsan {
    @Serial
    private static final long serialVersionUID = 0L;
    private String value;
    private String rawValue;
    private JsanType type;
    private int charStartAt;
    private int charEndAt;

    public int getCharStartAt() {
        return charStartAt;
    }

    public void setCharStartAt(int charStartAt) {
        this.charStartAt = charStartAt;
    }

    public int getCharEndAt() {
        return charEndAt;
    }

    public void setCharEndAt(int charEndAt) {
        this.charEndAt = charEndAt;
    }

    public Jsan(JsanType jsanType, String rawValue) {
        this.type = jsanType;
        this.rawValue = rawValue;
    }

    public JsanType getType() {
        return type;
    }

    protected void setType(JsanType type) {
        this.type = type;
    }

    public String getRawValue() {
        return rawValue;
    }

    protected void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Jsan{" +
                "value='" + value + '\'' +
                ", rawValue='" + rawValue + '\'' +
                ", type=" + type +
                '}';
    }
}
