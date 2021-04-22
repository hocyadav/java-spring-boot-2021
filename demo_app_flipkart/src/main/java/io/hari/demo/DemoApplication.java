package io.hari.demo;

import io.hari.demo.config.AppConfig;
import io.hari.demo.constant.AppConstant;
import io.hari.demo.dao.Entity1Dao;
import io.hari.demo.entity.Entity1;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {
    private final AppConfig config;
    private final Entity1Dao entity1Dao;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("config = " + config);
        final List<String> list = config.getList();
        System.out.println("list = " + list);
        final Map<String, String> map = config.getMap();
        System.out.println("map = " + map.get("key1"));
        System.out.println("constant1 = " + AppConstant.CONSTANT_1);
        System.out.println("AppConstant.errorMessage = " + AppConstant.ERROR_MESSAGE);
        System.out.println("AppConstant.SUCCESS_MESSAGE = " + AppConstant.SUCCESS_MESSAGE);

        final AppConfig.ClassA key1 = config.getObject().get("key1");
        System.out.println("key1 = " + key1);
        IntStream.range(0, 10)
                .forEach(i -> entity1Dao.save(Entity1.builder().name("name-"+i).build()));
        final List<Entity1> entity1s = entity1Dao.sqlMethod();
        System.out.println("entity1s = " + entity1s);

        final List<Entity1> entity1s1 = entity1Dao.sqlMethod2("name", 0, 3);
        System.out.println("entity1s1 = " + entity1s1);

    }
}
