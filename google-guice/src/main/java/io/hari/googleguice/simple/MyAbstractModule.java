package io.hari.googleguice.simple;

import com.google.inject.AbstractModule;
import io.hari.googleguice.simple2.impl.IShape;
import io.hari.googleguice.simple2.impl.MyShapeImpl1;
import io.hari.googleguice.simple2.impl.MyShapeImpl2;

public class MyAbstractModule extends AbstractModule {
    @Override
    protected void configure() {
//        super.configure();//working
//        bind(IShape.class).to(MyShapeImpl1.class);//working
        bind(IShape.class).to(MyShapeImpl2.class);//working
    }
}
