package com.example.sample1;

import org.springframework.stereotype.Component;

/**
 * @author HariomYadav
 * @since 07/02/21
 */
@Component
public class MyInterfaceImpl implements MyInterface{
    @Override
    public void foo1() {
        System.err.println("MyInterfaceImpl.foo1");
    }

    @Override
    public void foo2() {
        System.err.println("MyInterfaceImpl.foo2");
    }

    @Override
    public void defaultMethod() {
        System.err.println("MyInterfaceImpl.defaultMethod");
    }
}
