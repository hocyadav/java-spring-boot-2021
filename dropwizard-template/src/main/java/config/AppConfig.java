package config;

import config.config_factory.MyConfigFactory;
import io.dropwizard.Configuration;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AppConfig extends Configuration {
    @NotEmpty
    String appName;

    @NotEmpty
    String location;

    @Valid
    @NotNull
    MyConfigFactory threadPoolConfig = new MyConfigFactory();
}
