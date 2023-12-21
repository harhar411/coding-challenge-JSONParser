package org.jsonparser.jsantype;

import java.io.Serial;

public enum JsanType {
    OBJECT("^\\{{1}+[\\s\\S]*?\\}{1}+$"),
    ARRAY("^\\[{1}+[\\s\\S]*?\\]{1}+$"),
    STRING("^\"{1}+[\\s\\S]*?\"{1}+$"),
    NUMBER("[0-9]*+"),
    BOOLEAN("TRUE|FALSE|true|false"),
    NULL("NULL|null"),
    KEY("^\"{1}+[\\S]*?\"{1}+$"),
    FIELD("^\"{1}+.*?\"{1}+[\\s]*?:{1}+[\\s\\S]*?,{0,1}+$");
    @Serial
    private static final long serialVersionUID = 0L;
    private String pattern;

    public String getPattern() {
        return pattern;
    }

    JsanType(String pattern) {
        this.pattern = pattern;
    }

    public static boolean isObject(String value) {
        return value.matches(OBJECT.pattern);
    }

    public static boolean isArray(String value) {
        return value.matches(ARRAY.pattern);
    }

    public static boolean isString(String value) {
        return value.matches(STRING.pattern);
    }

    public static boolean isNumber(String value) {
        return value.matches(NUMBER.pattern);
    }

    public static boolean isBoolean(String value) {
        return value.matches(BOOLEAN.pattern);
    }

    public static boolean isNull(String value) {
        return value.matches(NULL.pattern);
    }

    public static boolean isKey(String value) {
        return value.matches(KEY.pattern);
    }

    public static boolean isField(String value) { return value.matches(FIELD.pattern); }
}
