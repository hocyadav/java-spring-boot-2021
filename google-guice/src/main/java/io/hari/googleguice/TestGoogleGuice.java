package io.hari.googleguice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.hari.googleguice.simple.MyAbstractModule;
import io.hari.googleguice.simple.MyService;
import io.hari.googleguice.simple2.MyShapeRequest;
import io.hari.googleguice.simple2.impl.IShape;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestGoogleGuice implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        //1. create injector instance, required Abstract Module instance
        Injector injector = Guice.createInjector(new MyAbstractModule());
        //2. create my service instance using injector
        MyService myService = injector.getInstance(MyService.class);
        myService.foo();

        //todo DONE : interface impl services
//        IShape iShape = injector.getInstance(IShape.class);
//        MyShapeRequest myShapeRequest = new MyShapeRequest(iShape);
//        myShapeRequest.makeRequest();

        //todo : using @Inject
//        IShape iShape1 = injector.getInstance(IShape.class);//not required, since we have added @Inject
        MyShapeRequest myShapeRequest1 = injector.getInstance(MyShapeRequest.class);//internally it will create IShapeImpl1 instance
        myShapeRequest1.makeRequest();
    }
}
