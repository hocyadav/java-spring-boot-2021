package io.hari.googleguice.simple2;

import com.google.inject.Inject;
import io.hari.googleguice.simple2.impl.IShape;
import lombok.extern.slf4j.Slf4j;

public class MyShapeRequest2 {
    private IShape shape;
    private String name;

    @Inject//this will go to AbstractModule class to see if binding is present or not for this interface : https://www.youtube.com/watch?v=tjHjHWNANGo&list=PLp0ed20U4R4jknb4xYdhx3yJn5RhWECxn&index=8
    public MyShapeRequest2(IShape shape, String name) {
        this.shape = shape;
        this.name = name;
    }

    public void makeRequest() {
        System.out.println("MyShapeRequest2.makeRequest2 - " + this.name);
        this.shape.draw();
    }
}
