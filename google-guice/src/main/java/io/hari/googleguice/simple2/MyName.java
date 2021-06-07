package io.hari.googleguice.simple2;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@BindingAnnotation
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
public @interface MyName { //https://www.youtube.com/watch?v=xfeEjAj2Rgs&list=PLp0ed20U4R4jknb4xYdhx3yJn5RhWECxn&index=9
}
