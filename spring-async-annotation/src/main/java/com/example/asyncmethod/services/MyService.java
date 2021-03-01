package com.example.asyncmethod.services;

import org.springframework.stereotype.Service;

/**
 * @author HariomYadav
 * @since 31/01/21
 */
@Service
public class MyService {

    public void foo() {
        System.out.println("MyService.foo name = " + Thread.currentThread().getName());
    }
}
