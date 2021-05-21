package resource;

import config.AppConfig;
import entity.PersonEntity;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Path("/test-api")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class MyResource {
    private final AppConfig config;

    @GET
    public PersonEntity getResource(@QueryParam("name")Optional<String> name) {
        final String fetchName = name.orElseGet(() -> "hello stranger");
        return PersonEntity.builder().name(fetchName).address(config.getLocation()).build();
    }
}
