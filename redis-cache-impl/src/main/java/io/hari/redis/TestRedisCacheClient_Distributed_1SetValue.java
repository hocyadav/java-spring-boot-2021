package io.hari.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Component
public class TestRedisCacheClient_Distributed_1SetValue implements CommandLineRunner {
    public static final String REDIS_ADDRESS = "redis://127.0.0.1:6379";

    @Override
    public void run(String... args) throws Exception {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(REDIS_ADDRESS);
//        RedissonClient client = Redisson.create();
        RedissonClient client = Redisson.create(config);
        System.out.println("client = " + client);

//        final RBucket<Object> rBucket = client.getBucket("bucket-hariom");
        final RMapCache<String, Object> anyMap = client.getMapCache("anyMap");
        anyMap.put("key1", new String("hariom"),
                10, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);

        final String key1 = String.class.cast(anyMap.get("key1"));
        System.out.println("key1 = " + key1);
    }
}
