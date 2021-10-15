package io.hari.java9to16feature.switchExpression;

public class SwitchExpression {
    public static void main(String[] args) {

        String input = "hari";
        String result = switch (input) {
            case "hari" -> "hariom yadav";
            case "om" -> "omprakash yadav";
            default -> "chandan yadav";
        };

        System.out.println("result = " + result);


        String result2 = switch (input) {
            case "hari" -> {
                //some processing
                //...
                System.out.println("do some processing...");

                yield "hariom yadav";
            }
            case "om" -> "omprakash yadav";
            default -> "chandan yadav";
        };

        System.out.println("result2 = " + result2);


    }
}
