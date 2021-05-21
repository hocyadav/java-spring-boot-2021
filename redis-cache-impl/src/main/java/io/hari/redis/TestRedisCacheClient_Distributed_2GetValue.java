package io.hari.redis;

import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Component
public class TestRedisCacheClient_Distributed_2GetValue implements CommandLineRunner {
    public static final String REDIS_ADDRESS = "redis://127.0.0.1:6379";

    @Override
    public void run(String... args) throws Exception {
        RedissonClient redissonClient = getRedissonClient();
        System.out.println("redissonClient = " + redissonClient);
        final RMapCache<String, Object> anyMap = redissonClient.getMapCache("anyMap");
        final String key1 = String.class.cast(anyMap.get("key1"));
        System.out.println("key1 = " + key1);
    }

    private RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(REDIS_ADDRESS);
        RedissonClient client = Redisson.create(config);
        return client;
    }
}
