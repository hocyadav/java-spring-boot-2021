package com.example.sample1;

import com.example.sample1.configs.ConfigClassValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Hariom Yadav
 * @create 5/20/2021
 */
@Component
@RequiredArgsConstructor
public class TestHibernateValidation implements CommandLineRunner {
    private final ConfigClassValidation config;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("config = " + config);
    }
}
