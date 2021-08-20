package com.hari.jpa1.vavr_java;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.junit.jupiter.api.Test;

/**
 * https://www.baeldung.com/vavr-either
 */
public class VavrEither {

    @Test
    public void test() {
        //at a time we can assign only one side value either left or right
        //assigning left type value
        Either<LeftClass, RightClass> either = Either.left(new LeftClass("left value 123"));
        System.out.println("either = " + either);

        either.right()//go right side
                .filter(rightClass -> {
                    System.out.println("right side log");
                    return true;
                })
                .forEach(rightClasses -> System.out.println("rightClasses = " + rightClasses));

        //assigning right type value
        Either<LeftClass, RightClass> either1 = Either.right(new RightClass("right 234"));
        System.out.println("either1 = " + either1);
    }

    @Value
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class LeftClass {
        String left;
    }

    @Value
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class RightClass {
        String righ;
    }
}
