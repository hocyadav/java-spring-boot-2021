import config.AppConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.SneakyThrows;
import resource.MyResource;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 * start application -> go to intellij config
 * server src/main/resources/my-config.yml
 */
public class MainApplication extends Application<AppConfig> {

    @SneakyThrows
    public static void main(String[] args) {
        new MainApplication().run(args);
    }
    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        final MyResource myResource = new MyResource(appConfig);
        environment.jersey().register(myResource);
    }
}
