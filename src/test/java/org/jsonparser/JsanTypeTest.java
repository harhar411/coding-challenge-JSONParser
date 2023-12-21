package org.jsonparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsonparser.jsanexception.NotJsanRootObjectException;
import org.jsonparser.jsantype.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class JsanTypeTest {
    private final Logger logger = LogManager.getLogger(JsanTypeTest.class);

    @Test
    void testCharacters() {
        String patternStr = "[\\s\\S]*";
        logger.info("asdsd\n\t{},123123#$@@#asd".matches(patternStr));
    }

    @Test
    void splitStringByComma() {
        String patternStr = "[^,]*,{0,1}";
        String value = "{aaa},{{sasd},asd}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(value);

        logger.info(matcher.groupCount());

        while(matcher.find())
            System.out.println((matcher.group()));

        logger.info(value.matches(patternStr));
    }

    @Test
    void testStringContainedInCurlyBracesRoot() {
        String patternStr = "^\\{[\\S\\s]*\\}$";
        String value = "{aaa" +
                "\n}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(value);

        logger.info(matcher.groupCount());

        while(matcher.find())
            System.out.println((matcher.group()));

        logger.info(value.matches(patternStr));
    }

    @Test
    void testKey() {
        String jsonString = "\"asdasd\"";

        Pattern pattern = Pattern.compile(JsanType.KEY.getPattern());
        Matcher matcher = pattern.matcher(jsonString);

        logger.info(matcher.find());
        logger.info(matcher.matches());
    }

    @Test
    void testString() {
        String jsonString = "\"as\ndasd\"";

        Pattern pattern = Pattern.compile(JsanType.STRING.getPattern());
        Matcher matcher = pattern.matcher(jsonString);

        logger.info(matcher.find());
        logger.info(matcher.matches());
    }

    @Test
    void testArray() {
        String jsonString = "[ab,cdf]";

        Pattern pattern = Pattern.compile(JsanType.ARRAY.getPattern());
        Matcher matcher = pattern.matcher(jsonString);

        logger.info(matcher.find());
        logger.info(matcher.matches());
    }

    @Test
    void testObject() {
        String jsonString = "{\n" +
                "  \"key1\": true,\n" +
                "  \"key2\": false,\n" +
                "  \"key3\": null,\n" +
                "  \"key4\": \"value\",\n" +
                "  \"key5\": 101\n" +
                "}";

        Pattern pattern = Pattern.compile(JsanType.OBJECT.getPattern());
        Matcher matcher = pattern.matcher(jsonString.trim());

        logger.info(matcher.groupCount());
        logger.info(matcher.find());
        logger.info(matcher.matches());

        JsanObject jsanObject = new JsanObject(jsonString);
        logger.info("test : " + jsanObject.getValue());
    }

    @Test
    void testNumber() {
        String jsonString = "9182739182370";

        Pattern pattern = Pattern.compile(JsanType.NUMBER.getPattern());
        Matcher matcher = pattern.matcher(jsonString);

        logger.info(matcher.matches());
        logger.info(matcher.find());
    }

    @Test
    void testBoolean() {
        String jsonString = "TRUE";

        Pattern pattern = Pattern.compile(JsanType.BOOLEAN.getPattern());
        Matcher matcher = pattern.matcher(jsonString);

        logger.info(matcher.matches());
        logger.info(matcher.find());
    }

    @Test
    void testNull() {
        String jsonString = "null";

        Pattern pattern = Pattern.compile(JsanType.NULL.getPattern());
        Matcher matcher = pattern.matcher(jsonString);

        logger.info(matcher.matches());
        logger.info(matcher.find());
    }

    @Test
    void testField() {
        String jsonString = "\"key-n\"    \n: \n     \t\"asdas{]d";

//        Pattern pattern = Pattern.compile(JsonType.FIELD.getPattern());
        Pattern pattern = Pattern.compile("\".*\"[\\s]*:[\\s\\S]*,{0,1}");
        Matcher matcher = pattern.matcher(jsonString.trim());

        logger.info(matcher.find());
        logger.info(matcher.matches());
    }

    @Test
    void testKeyValuePair() {
        String jsonString = "{\n" +
                "  \"key\": \"va\nlue\",\n" +
                "  \"key-n\": 101,\n" +
                "  \"key-o\": {},\n" +
                "  \"key-l\": []\n" +
                "}";

        String patternStr = "\".*\"[\\s]*:[\\S\\s]*,{0,1}";
//        String patternStr = JsanType.FIELD.getPattern();

        logger.info(patternStr);

        Pattern pattern = Pattern.compile(patternStr);
        JsanObject jsanObject = new JsanObject(jsonString.trim());
        Matcher matcher = pattern.matcher(jsanObject.getValue());

        logger.info(jsanObject.getValue());

        while(matcher.find())
            logger.info("'" + matcher.group() + "'");
    }

    @Test
    void testRun() {
        String jsonString = "{\n" +
                "  \"key\": \"value\",\n" +
                "  \"key-n\": 101,\n" +
                "  \"key-o\": {},\n" +
                "  \"key-l\": []\n" +
                "}";

        Jsan jsan;
        if(JsanType.isObject(jsonString)) {
            jsan = new JsanObject(jsonString);

            logger.info(jsan.toString());
            logger.info(jsan.getValue());
        }
    }

    @Test
    void getText() {
        JsanObject jro = new JsanObject("the rawValue");
        Jsan jsan = new Jsan(JsanType.KEY, "the rawValue");

        String s = jro.toString();

        logger.info(s);
        logger.info(jsan.toString());
    }

    @Test
    void test() {
        String jsonString = "{\n" +
                "  \"key\": \"value\",\n" +
                "  \"key-n\": 101,\n" +
                "  \"key-o\": {},\n" +
                "  \"key-l\": []\n" +
                "    }";

        if(!JsanType.isObject(jsonString))
            throw new NotJsanRootObjectException(jsonString + "\n\nNot a valid JsanRootObject starting at character 0 and ending at character " + jsonString.trim().length());

        JsanObject jro = new JsanObject(jsonString);
        jro.setCharStartAt(0);
        jro.setCharEndAt(jsonString.trim().length());

        JsanObject jsanRootObject = Parser.parseJsanRootObject(jsonString);

        List<JsanField> jsanFields = Parser.parseFields(jsanRootObject);

        for(JsanField jsanField : jsanFields) {
            logger.info("'" + jsanField.getValue() + "'");

            logger.info(Parser.parseJsanKey(jsanField.getRawValue(), jsanField).getValue());
            logger.info(Parser.parseJsanKey(jsanField.getRawValue(), jsanField).getRawValue());
            logger.info("field start : " + jsanField.getCharStartAt());
            logger.info("field end : " + jsanField.getCharEndAt());
            logger.info("key start : " + Parser.parseJsanKey(jsanField.getRawValue(), jsanField).getCharStartAt());
            logger.info("key end : " + Parser.parseJsanKey(jsanField.getRawValue(), jsanField).getCharEndAt());
            logger.info("value : " + Parser.parseJsanValue(jsanField.getRawValue(), jsanField).getRawValue());
            Jsan jsanValue = Parser.parseJsanValue(jsanField.getRawValue(), jsanField);

        }

    }

    @Test
    void testFieldTest() {
        logger.info(JsanType.isArray("  []  "));

    }
}