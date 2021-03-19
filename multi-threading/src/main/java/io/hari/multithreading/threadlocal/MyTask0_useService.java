package io.hari.multithreading.threadlocal;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class MyTask0_useService {

    public void foo() {
        //some task
        final GlobalService globalService = new GlobalService();//every time it will create new obj
        final String serviceOutput = globalService.dummyService();

        System.out.println("globalService.dummyService()... "+serviceOutput);
    }
}
