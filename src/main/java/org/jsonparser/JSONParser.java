package org.jsonparser;

import org.jsonparser.jsantype.JsanField;
import org.jsonparser.jsantype.JsanObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class JSONParser {
    public static void main(String... args) {
        if(args.length != 1)
            System.exit(1);

        try {
            File file = new File(args[0]);
            String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            JsanObject jsanObject = Parser.parseJsanRootObject(content);

            System.out.println("This is a valid JSON : ");
            System.out.println("--------------------------------");
            System.out.println(content);
            System.out.println("--------------------------------");

            for(JsanField jf : jsanObject.getFields()) {
                System.out.println("---FIELDS---");
                System.out.println("   KEY : " + jf.getKey().getValue());
                System.out.println("   RAW VALUE : " + jf.getJsanValue().getRawValue());
                System.out.println("   VALUE : " + jf.getJsanValue().getValue());
                System.out.println("   VALUE TYPE : " + jf.getJsanValue().getType());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }
}