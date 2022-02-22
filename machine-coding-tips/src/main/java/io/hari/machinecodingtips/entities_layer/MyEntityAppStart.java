package io.hari.machinecodingtips.entities_layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // === @Configuration + @EnableAutoConfiguration + @ComponentScan
@ComponentScan(basePackages = "io.hari.machinecodingtips.entities_layer") //https://www.baeldung.com/spring-component-scanning
public class MyEntityAppStart implements CommandLineRunner {
    @Autowired
    private MyService myService;

//    @Autowired
//    MyService2 myService2; //not work because present in another package

    public static void main(String[] args) {
        System.out.println("hello harry");
        SpringApplication.run(MyEntityAppStart.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myService.foo();
    }
}
// ✅ run package specific, make sure it will not take any other package beans