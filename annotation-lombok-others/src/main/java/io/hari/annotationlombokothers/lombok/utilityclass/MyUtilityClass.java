package io.hari.annotationlombokothers.lombok.utilityclass;

import lombok.Data;
import lombok.experimental.UtilityClass;

/**
 * - 1. make class final
 * - 2. make method static
 * - 3. make fields static
 * - 4. add private constructor + throw exception
 */
@UtilityClass
public class MyUtilityClass {
    private String name = "Hariom Yadav";//this can be used inside utility class method
    public Integer rollNo = 495452;//this can be used outside

    public void foo() {
        System.out.println("MyUtilityClass.foo");
        System.out.println("name = " + name);
    }
}
