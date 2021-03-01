package com.example.sample1;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author HariomYadav
 * @since 07/02/21
 */
public interface MyInterface {//public keyword is optional , since all are public
    // https://dzone.com/articles/interface-default-methods-java

    public void foo1();

    void foo2();

    public default void defaultMethod() {
        System.err.println("MyInterface.foo..");
    }

    default String defaultMethod2(String str) {
        return str.toUpperCase();
    }

    default BigDecimal defaultMethod3(BigDecimal decimal) {
        MathContext mathContext = new MathContext(4);
        final BigDecimal round = decimal.round(mathContext);
        System.out.println("round = " + round);
        final BigDecimal decimal1 = round.setScale(2);
        System.out.println("decimal1 = " + decimal1);
        return decimal1;
    }

    static BigDecimal staticMethod(BigDecimal decimal) {
        MathContext mathContext = new MathContext(4);
        final BigDecimal round = decimal.round(mathContext);
        System.out.println("round = " + round);
        final BigDecimal decimal1 = round.setScale(2);
        System.out.println("decimal1 = " + decimal1);
        return decimal1;
    }
}
