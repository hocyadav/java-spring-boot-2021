package io.hari.annotationlombokothers.spring_config.method1.yml_entity;

import lombok.Data;

@Data//this is for access value, access yml value
public class MyYamlConfig2 {
    String config2;
    MyClass myClass;

    @Data
    public static class MyClass {
        String rollNumber;
    }

    //use class object as field, inner class or other class entity
    //explore : create inner static class as object
}
/**
anyname.config2="omp"
anyname.myClass.rollNumber=495452
 */
