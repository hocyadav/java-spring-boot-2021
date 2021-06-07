package io.hari.googleguice.simple2;

import com.google.inject.Inject;
import io.hari.googleguice.simple2.impl.IShape;

public class MyShapeRequest {
    IShape shape;

    @Inject
    public MyShapeRequest(IShape shape) {
        this.shape = shape;
    }

    public void makeRequest() {
        System.out.println("MyShapeRequest.makeRequest");
        this.shape.draw();
    }
}
