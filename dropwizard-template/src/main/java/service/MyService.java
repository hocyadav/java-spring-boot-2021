package service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Hariom Yadav
 * @create 28-05-2021
 */
@Slf4j
@NoArgsConstructor
public class MyService {

    public String sampleService() {
        log.info("MyService.sampleService");
        return "sample service response";
    }
}
