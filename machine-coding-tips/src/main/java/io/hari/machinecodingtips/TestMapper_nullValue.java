package io.hari.machinecodingtips;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Arrays;

public class TestMapper_nullValue {

    @SneakyThrows
    public static void main(String[] args) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        System.out.println("writeValueAsString = " + objectMapper.writeValueAsString("hariom"));
//        System.out.println("writeValueAsString = " + objectMapper.writeValueAsString(""));
//        String s = objectMapper.writeValueAsString(null);
//        System.out.println("writeValueAsString = " + s);
//        System.out.println("empty = " + s.isEmpty());
//        System.out.println("null = " + s == null);
//        System.out.println("writeValueAsString = " + objectMapper.writeValueAsString("null"));

//        new ObjectMapper().readValue("AI=21L=684'']\[]", String.class);
//        String readValue = new ObjectMapper().readValue("AI=21L=684'']\\[]", String.class);
//        System.out.println("readValue = " + readValue);


//        String str1 = new String("AI=21L=684'']\\[]");
        String str1 = new String("AI=21L=684'']\\[]AI=21L=684'']\\[]");
//        String str1 = new String("\\[]");
        String str2 = new String(str1);// no serialize and assign
        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);

        String str1_inSerializeForm = new ObjectMapper().writeValueAsString(str1);// serialize and then assign : sol ✅
        System.out.println("str1_ = " + str1_inSerializeForm);
        String str2_ = new String(str1_inSerializeForm);// now we can store serialize in another field
        System.out.println("str2_ = " + str2_);

        String str4 = new ObjectMapper().readValue(str1_inSerializeForm, String.class);
        System.out.println("str4 = " + str4);


        // escape and unescape
        String replace = str1.replace("\\", "\\\\");
        System.out.println("replace = " + replace);


//        StringEscapeUtils.escapeJava("");

        byte[] bytes = str1.getBytes();
        System.out.println("\nstr1 = " + str1);
        System.out.println("Arrays.toString(bytes) = " + Arrays.toString(bytes));
        // convert byte string to string

        String s = new String(bytes);
        System.out.println("s = " + s);
        String replace1 = new String(bytes).replaceAll("\\\\", "\\\\\\\\");
        System.out.println("replace1 = " + replace1);

        System.out.println("contains = " + s.contains("\\"));

        byte[] bytes1 = str1.replace("\\", "\\\\").getBytes();
        System.out.println("str1.replace " + str1.replace("\\", "\\\\"));
        System.out.println("Arrays.toString(bytes) = " + Arrays.toString(bytes1));



//        String unescapeJava = StringEscapeUtils.unescapeJava(str1.replace("\\", "\\\\"));
//        System.out.println("unescapeJava = " + unescapeJava);
//
//        String escapeJava = StringEscapeUtils.escapeJava(str1.replace("\\", "\\\\"));
//        System.out.println("escapeJava = " + escapeJava);



    }
}
