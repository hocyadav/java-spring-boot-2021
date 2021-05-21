package io.hari.redis;

import io.hari.redis.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Component
@RequiredArgsConstructor
public class TestCache implements CommandLineRunner {//working
    private final MyService service;

    @Override
    public void run(String... args) throws Exception {
        service.findById(Long.valueOf(10));
        Thread.sleep(2000);
        service.findById(Long.valueOf(10));
        Thread.sleep(2000);
        service.findById(Long.valueOf(0));
        Thread.sleep(2000);
        service.findById(Long.valueOf(20));
    }
}
/** redis cache monitor output
 127.0.0.1:6379> monitor
 OK
 1621576135.641386 [0 127.0.0.1:50646] "PING"
 1621576135.648938 [0 127.0.0.1:50646] "GET" "MyServiceCache::10"
 1621576135.663173 [0 127.0.0.1:50646] "SET" "MyServiceCache::10" "\xac\xed\x00\x05sr\x00\x1dio.hari.redis.entity.Customer\x9c\xa6\xf8*\xe10};\x02\x00\x02L\x00\aaddresst\x00\x12Ljava/lang/String;L\x00\x04nameq\x00~\x00\x01xpt\x00\tbangaloret\x00\x0bcustomer-10"
 1621576137.674025 [0 127.0.0.1:50646] "GET" "MyServiceCache::10"
 1621576139.693291 [0 127.0.0.1:50646] "GET" "MyServiceCache::0"
 1621576139.695045 [0 127.0.0.1:50646] "SET" "MyServiceCache::0" "\xac\xed\x00\x05sr\x00\x1dio.hari.redis.entity.Customer\x9c\xa6\xf8*\xe10};\x02\x00\x02L\x00\aaddresst\x00\x12Ljava/lang/String;L\x00\x04nameq\x00~\x00\x01xpt\x00\tbangaloret\x00\ncustomer-0"
 1621576141.703629 [0 127.0.0.1:50646] "GET" "MyServiceCache::20"
 1621576141.707842 [0 127.0.0.1:50646] "SET" "MyServiceCache::20" "\xac\xed\x00\x05sr\x00\x1dio.hari.redis.entity.Customer\x9c\xa6\xf8*\xe10};\x02\x00\x02L\x00\aaddresst\x00\x12Ljava/lang/String;L\x00\x04nameq\x00~\x00\x01xpt\x00\tbangaloret\x00\x0bcustomer-20"
 */
