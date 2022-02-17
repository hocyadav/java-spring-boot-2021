package io.hari.machinecodingtips.regex;

public class TestA {
    public static void main(String[] args) {
        System.out.println("s = " + "[:-_@#A-Za-z 0-9.$)\\\\G\\-\\\\E(^*]+");
        System.out.println("s = " + "[:-_@#A-Za-z 0-9.$)\\-(^*]+");// even number valid
        System.out.println("s = " + "[:-_@#A-Za-z 0-9.$)\\\-(^*]+");//3 slash invalid, even number valid
    }
}
