package io.hari.multithreading.threadlocal.prod;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class TestThreadLocal {
    public static void main(String[] args) {
        final ExecutorService pool = Executors.newFixedThreadPool(10);

//        for (int i = 0; i < 1000; i++) {//working
//            pool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    final MyService task = new MyService(new ExternalService1(), new ExternalService2());
//                    task.myService();
//                }});
//        }

//        for (int i = 0; i < 1000; i++) {//working
//            pool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    final MyService_Adv service_adv = new MyService_Adv();
//                    service_adv.myService();
//                }});
//        }

        for (int i = 0; i < 1000; i++) {//working
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    final MyService_Adv2 service_adv2 =
                            new MyService_Adv2(ThreadLocalGlobalServiceHolder.externalService1.get(),
                                    ThreadLocalGlobalServiceHolder.externalService2.get());
                    service_adv2.myService();
                }});
        }


    }
}
