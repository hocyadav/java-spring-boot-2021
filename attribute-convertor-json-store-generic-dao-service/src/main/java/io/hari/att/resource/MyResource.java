package io.hari.att.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyResource {

    @GetMapping
    public void testException() throws Exception {
//        throw new RuntimeException("test runtime exception");//working
        throw new IllegalStateException("test runtime exception");//working
//        throw new Exception("ex....."); //working
    }
}
