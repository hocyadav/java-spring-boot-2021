package io.hari.googleguice.simple;

import com.google.inject.AbstractModule;
import io.hari.googleguice.simple2.MyName;
import io.hari.googleguice.simple2.impl.IShape;
import io.hari.googleguice.simple2.impl.MyShapeImpl1;
import io.hari.googleguice.simple2.impl.MyShapeImpl2;

public class MyAbstractModule extends AbstractModule {
    @Override
    protected void configure() {
//        super.configure();//working : this is for simple class that is concrete
//        bind(IShape.class).to(MyShapeImpl1.class);//working
        bind(IShape.class).to(MyShapeImpl2.class);//working : this is use for interface -> to concrete impl class, or concrete parent -> concrete child class

        bind(String.class).toInstance("hariom yadav");//working : using MyShapeRequest2

        bind(String.class).annotatedWith(MyName.class).toInstance("omprakash yadav");//working : using MyShapeRequest3
    }
}
