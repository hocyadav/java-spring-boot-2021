package io.hari.annotationlombokothers.spring_config.method2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestConfig implements ApplicationRunner {
    private MyYamlConfig01 myYamlConfig01;//other way we can inject using @ReqArgConst
//    @Autowired//optional , without also working
    public TestConfig(MyYamlConfig01 myYamlConfig01) {
        this.myYamlConfig01 = myYamlConfig01;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("myYamlConfig01 = " + myYamlConfig01);
        System.out.println("name = " + myYamlConfig01.getName());
        System.out.println("email = " + myYamlConfig01.getEmail());
    }
}
