package io.hari.machinecodingtips;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * https://sharegpt.com/c/LxsaRfO
 */
public class GoogleCacheExample {

    @Test
    @SneakyThrows
    public void cacheTesting(){
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build();

        // Adding an entry to the cache
        cache.put("key", "value123");
        Thread.sleep(1000);

        // Retrieving an entry from the cache
        String value = cache.getIfPresent("key");
        System.out.println(value); // Output: "value"
    }

    @Test
    public void customEviction(){
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(notification -> {
                    // Custom eviction logic
                    System.out.println("Entry removed from cache: " + notification.getKey());
                })
                .build();

        // Adding an entry to the cache
        cache.put("key", "value234");

        // key present in cache
        System.out.println("cache.getIfPresent = " + cache.getIfPresent("key"));

        // Removing an entry from the cache
        cache.invalidate("key");

        // key not present in cache
        System.out.println("cache.getIfPresent = " + cache.getIfPresent("key"));
    }

    /**
     * In this example, the cache will start with an initial capacity of 10
     * and can grow up to a maximum size of 100 entries.
     * Once the maximum size is reached, the cache will evict entries according to the specified eviction policy.
     */
    @Test
    public void initialCapacityAndMaximumSize(){
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(10)// initial size
                .maximumSize(100) // max size
                .build();
        cache.put("name", "hariom");
        System.out.println("cache= " + cache.getIfPresent("name"));
    }

    /**
     * In this example, the cache will start with an initial capacity of 100 entries
     * and there is no maximum size. The cache can grow as large as needed,
     * and entries will only be evicted according to the specified eviction policy
     * (such as time-based expiration or removal listeners).
     */
    @Test
    public void foo(){
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(Long.MAX_VALUE)
                .build();

        cache.put("name", "hariom");
        System.out.println("cache= " + cache.getIfPresent("name"));

    }
}
