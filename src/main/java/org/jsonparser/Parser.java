package org.jsonparser;

import org.jsonparser.jsanexception.*;
import org.jsonparser.jsantype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static JsanObject parseJsanRootObject(String jsonString) {
        jsonString = jsonString.trim();
        JsanObject jsanObject = null;
        
        if(JsanType.isObject(jsonString)) {
            jsanObject = new JsanObject(jsonString);

            jsanObject.setFields(parseFields(jsanObject));
        }
        else
            throw new NotJsanRootObjectException("Not a JsanRootObject starting at character 0 and ending at character "
                    + jsonString.length()
                    + "\nof JSON :\n"
                    + jsonString
            );

        return jsanObject;
    }

    public static List<JsanField> parseFields(JsanObject jsanObject) throws NotJsanFieldException {
        String jsonString = jsanObject.getValue();

        List<JsanField> jsanFieldList = new ArrayList<>();
        int curlies = 0;
        boolean doubleQuotes = false;
        int braces = 0;
        int i = 0, start = 0;
        while(i < jsonString.length()) {
            Character c = jsonString.charAt(i);

            switch (c) {
                case '{' :
                    curlies++;
                    break;
                case '}' :
                    if (curlies > 0)
                        curlies--;
                    break;
                case '[' :
                    braces++;
                    break;
                case ']' :
                    if (braces > 0)
                        braces--;
                    break;
                case '"' :
                    if (doubleQuotes)
                        doubleQuotes = false;
                    else
                        doubleQuotes = true;
                    break;
                case ',' :
                    if(curlies == 0 && braces == 0 && !doubleQuotes) {
                        addJsanField(jsanObject.getRawValue(), jsonString.substring(start, i).trim(), jsanFieldList, start, i);
                        start = i + 1;
                    }
                    break;
            }
            i++;
        }

        addJsanField(jsanObject.getRawValue(), jsonString.substring(start, i).trim(), jsanFieldList, start, i);

        return jsanFieldList;
    }

    private static void addJsanField(String jsonRaw, String subStr, List<JsanField> jsanFieldList, int charStart, int charEnd) throws NotJsanFieldException {
        if(!JsanType.isField(subStr))
            throw new NotJsanFieldException("Not a valid JsanField starting at character "
                    + charStart + " and ending at character "
                    + charEnd + " : " + subStr
                    + "\nof JSON :\n"
                    + jsonRaw
            );

        JsanField jsanField = new JsanField(subStr);
        jsanField.setCharStartAt(charStart);
        jsanField.setCharEndAt(charEnd);
        jsanField.setKey(Parser.parseJsanKey(jsonRaw, jsanField));
        jsanField.setJsanValue(Parser.parseJsanValue(jsonRaw, jsanField));

        jsanFieldList.add(jsanField);
    }

    public static JsanKey parseJsanKey(String jsonRaw, JsanField jsanField) throws NotJsanKeyException {
        String jsonString = jsanField.getRawValue();
        String patternStr = "^\\s*+\"{1}+[a-zA-Z_0-9-]++\"{1}+\\s*+:{1}+";

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(jsonString);

        if(!matcher.find())
            throw new NotJsanFieldException("Not a valid JsanField starting at character "
                    + jsanField.getCharStartAt() + " and ending at character "
                    + jsanField.getCharEndAt() + " : " + jsanField.getValue()
                    + "\nof JSON :\n"
                    + jsonRaw
            );

        jsonString = jsonString.substring(0, matcher.end() - 1);

        if(!JsanType.isKey(jsonString))
            throw new NotJsanFieldException("Not a valid JsanKey starting at character "
                    + jsanField.getCharStartAt() + " and ending at character "
                    + (matcher.end() + jsanField.getCharStartAt()) + " : " + jsanField.getValue()
                    + "\nof JSON :\n"
                    + jsonRaw
            );

        JsanKey jsanKey = new JsanKey(jsonString);
        jsanKey.setCharStartAt(jsanField.getCharStartAt());
        jsanKey.setCharEndAt(matcher.end() - 1);

        return jsanKey;
    }

    public static Jsan parseJsanValue(String jsonRaw, JsanField jsanField) {
        String patternStr = "^\\s*+\"{1}+[a-zA-Z_0-9-]++\"{1}+\\s*+:{1}+";

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(jsanField.getRawValue());

        if(!matcher.find())
            throw new NotJsanValueException("Not a valid JsanField starting at character "
                    + jsanField.getCharStartAt() + " and ending at character "
                    + (jsanField.getCharStartAt() + matcher.end()) + " : " + jsanField.getValue()
                    + "\nof JSON :\n"
                    + jsonRaw
            );

        String valueStr = jsanField.getRawValue().substring(matcher.end()).trim();

        Jsan jsan = null;
        if(JsanType.isObject(valueStr)) {
            jsan = new JsanObject(valueStr);
        }
        else if(JsanType.isArray(valueStr)) {
            jsan = new JsanArray(valueStr);
        }
        else if(JsanType.isNull(valueStr)) {
            jsan = new JsanNull(valueStr);
        }
        else if(JsanType.isBoolean(valueStr)) {
            jsan = new JsanBoolean(valueStr);
        }
        else if(JsanType.isString(valueStr)) {
            jsan = new JsanString(valueStr);
        }
        else if(JsanType.isNumber(valueStr)) {
            jsan = new JsanNumber(valueStr);
        }
        else {
            throw new NotJsanValueException("Not a valid JsanValue starting at character "
                    + jsanField.getCharStartAt() + " and ending at character "
                    + (jsanField.getCharStartAt() + matcher.end()) + " : " + jsanField.getValue()
                    + "\nof JSON :\n"
                    + jsonRaw
            );
        }

        jsan.setCharStartAt(jsanField.getCharStartAt());
        jsan.setCharEndAt(matcher.end());

        return jsan;
    }
}
