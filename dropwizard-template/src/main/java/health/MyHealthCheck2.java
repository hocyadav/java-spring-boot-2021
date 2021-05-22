package health;

import com.codahale.metrics.health.HealthCheck;

/**
 * @Author Hariom Yadav
 * @create 5/22/2021
 */
public class MyHealthCheck2 extends HealthCheck {//app will start but we can see status of this health check http://localhost:8081/healthcheck
    @Override
    protected Result check() throws Exception {
        return Result.healthy("app is healthy - hariom");
//        return Result.unhealthy("not healthy - hariom");
    }
}
