package config;

import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

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
}
