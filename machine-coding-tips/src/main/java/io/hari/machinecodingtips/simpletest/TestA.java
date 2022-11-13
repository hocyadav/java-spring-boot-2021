package io.hari.machinecodingtips.simpletest;

public class TestA {
    public static void main(String[] args) {
        String s = "10.1";
        Double d = Double.parseDouble(s);
        System.out.println("d = " + d);
        int intValue = d.intValue();
        System.out.println("d = " + intValue);

    }
}
