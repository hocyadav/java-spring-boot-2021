package io.hari.javareactiveframework.core_concept.util;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
public class Test_BiFunction {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> biFunction = new BiFunction<>() {
            @Override
            public Integer apply(Integer type1, Integer type2) {//this will be executed when we call apply
                //do some operation on type1 and type2
                int type3 = type1 + type2;
                return type3;//and return type3
            }
        };
        Integer type3 = biFunction.apply(1, 2);//execute apply method
        System.out.println("type3 = " + type3);//3
    }
}
