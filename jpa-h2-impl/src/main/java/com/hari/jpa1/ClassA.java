package com.hari.jpa1;

public class ClassA {
    public static void main(String[] args) {
        int result = fact(4);
        System.out.println("result = " + result);
    }

    static int fact(int n) {
        if (n < 1) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int temp = fact(n - 1);//black box
        System.out.println("temp = " + temp);
        int result = n * temp;
        return result;
    }
}
