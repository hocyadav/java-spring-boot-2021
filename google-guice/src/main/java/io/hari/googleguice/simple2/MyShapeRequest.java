package io.hari.googleguice.simple2;

import com.google.inject.Inject;
import io.hari.googleguice.simple2.impl.IShape;

public class MyShapeRequest {
    IShape shape;

    @Inject//this will go to AbstractModule class to see if binding is present or not for this interface : https://www.youtube.com/watch?v=tjHjHWNANGo&list=PLp0ed20U4R4jknb4xYdhx3yJn5RhWECxn&index=8
    public MyShapeRequest(IShape shape) {
        this.shape = shape;
    }

    public void makeRequest() {
        System.out.println("MyShapeRequest.makeRequest");
        this.shape.draw();
    }
}
