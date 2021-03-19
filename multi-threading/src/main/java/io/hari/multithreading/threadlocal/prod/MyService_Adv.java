package io.hari.multithreading.threadlocal.prod;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class MyService_Adv {
    //not required
//    ExternalService1 externalService1;
//    ExternalService2 externalService2;
//
//    public MyService_Adv(ExternalService1 externalService1, ExternalService2 externalService2) {
//        this.externalService1 = externalService1;
//        this.externalService2 = externalService2;
//    }


    public void myService() {
//        final String output1 = this.externalService1.service1();//not required
//        final String output2 = this.externalService2.service2();

        final ExternalService1 externalService1 = ThreadLocalGlobalServiceHolder.externalService1.get();
        final ExternalService2 externalService2 = ThreadLocalGlobalServiceHolder.externalService2.get();
        final String output1 = externalService1.service1();
        final String output2 = externalService2.service2();

        final String combineResult = output1 + " - " + output2;
        System.out.println("combineResult = " + combineResult);
    }
}
