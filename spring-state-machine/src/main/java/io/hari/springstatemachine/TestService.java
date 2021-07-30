package io.hari.springstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class TestService implements CommandLineRunner {

    @Autowired
    MyService myService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("TestService.run");
        myService.changeEntityEvent();
    }
}
