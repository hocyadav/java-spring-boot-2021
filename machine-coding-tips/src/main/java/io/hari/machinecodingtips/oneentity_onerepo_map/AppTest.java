package io.hari.machinecodingtips.oneentity_onerepo_map;

import io.hari.machinecodingtips.oneentity_onerepo_map.entity.MyEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.hari.machinecodingtips.oneentity_onerepo"})
public class AppTest implements CommandLineRunner {

//    @Autowired
//    MyEntityRepo myEntityRepo;

    public static void main(String[] args) {
        System.out.println("args = " + args);
        SpringApplication.run(AppTest.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("myEntity = " + MyEntity.fakerData());
        System.out.println("myEntity = " + MyEntity.fakerData());
        System.out.println("myEntity = " + MyEntity.fakerData());

//        HashMap<Integer, MyEntity> all = myEntityRepo.getAll();
//        System.out.println("all = " + all);
    }
}
