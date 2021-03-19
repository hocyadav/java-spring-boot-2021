package io.hari.multithreading.threadlocal;

import io.reactivex.observers.TestObserver;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class MyTask0_useService2 {

    public void foo() {
        //some task
//        final GlobalService globalService = new GlobalService();//every time it will create new obj
        final GlobalService globalService = Test.globalService.get();//get service only for thread , if 10 thread pool then 10 obj
        final String serviceOutput = globalService.dummyService();

        System.out.println("globalService.dummyService()2... "+serviceOutput);
    }
}
