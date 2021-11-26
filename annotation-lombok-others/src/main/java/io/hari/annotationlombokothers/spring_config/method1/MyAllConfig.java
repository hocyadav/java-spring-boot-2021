package io.hari.annotationlombokothers.spring_config.method1;

import io.hari.annotationlombokothers.spring_config.method1.yml_entity.MyYamlConfig;
import io.hari.annotationlombokothers.spring_config.method1.yml_entity.MyYamlConfig2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Component//app start, not read yml file , use @Configuration
@Configuration//*start with config value from yml
public class MyAllConfig {
    @Bean
    @ConfigurationProperties(value = "myapp.config")
    public MyYamlConfig myYamlConfig(){
        return new MyYamlConfig();
    }

    @Bean
    @ConfigurationProperties(value = "anyname")
    public MyYamlConfig2 myYamlConfig2(){
        return new MyYamlConfig2();
    }


}
