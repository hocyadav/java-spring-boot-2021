package io.hari.multithreading.threadlocal.prod;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class ThreadLocalGlobalServiceHolder {
    //field variable - like global field variables, wrapped inside ThreadLocal
    public static ThreadLocal<ExternalService1> externalService1 = new ThreadLocal<ExternalService1>(){
        @Override
        protected ExternalService1 initialValue() {
            return new ExternalService1();
        }
        @Override
        public ExternalService1 get() {
            return super.get();
        }
    };

    public static ThreadLocal<ExternalService2> externalService2 = new ThreadLocal<ExternalService2>() {
        @Override
        protected ExternalService2 initialValue() {
            return new ExternalService2();
        }

        @Override
        public ExternalService2 get() {
            return super.get();
        }
    };
}
