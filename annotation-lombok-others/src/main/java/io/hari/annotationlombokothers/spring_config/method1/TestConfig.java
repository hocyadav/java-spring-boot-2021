package io.hari.annotationlombokothers.spring_config.method1;

import io.hari.annotationlombokothers.spring_config.method1.yml_entity.MyYamlConfig;
import io.hari.annotationlombokothers.spring_config.method1.yml_entity.MyYamlConfig2;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestConfig implements ApplicationRunner {
    private final MyAllConfig myAllConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MyYamlConfig yamlConfig = myAllConfig.myYamlConfig();
        System.out.println("yamlConfig = " + yamlConfig);

        MyYamlConfig2 myYamlConfig2 = myAllConfig.myYamlConfig2();
        System.out.println("myYamlConfig2 = " + myYamlConfig2);
        MyYamlConfig2.MyClass config2 = myYamlConfig2.getMyClass();
        System.out.println("getRollNumber = " + config2.getRollNumber());

    }
}
