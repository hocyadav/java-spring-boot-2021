package io.hari.annotationlombokothers.spring_config.method1.yml_entity;

import lombok.Data;
@Data//this is for access value, access yml value
public class MyYamlConfig {
    String name;
    String email;

    //use class object as field, inner class or other class entity
    //explore : create inner static class as object
}
/**
 myapp.config.name="hariom"
 myapp.config.email="hariom@fk.com"
 */
