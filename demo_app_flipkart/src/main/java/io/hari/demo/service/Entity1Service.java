package io.hari.demo.service;

import io.hari.demo.dao.Entity1Dao;
import io.hari.demo.entity.Entity1;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Entity1Service {
    private final Entity1Dao entity1Dao;

    public List<Entity1> test(@NonNull String inputNullCheck) {
        final List<Entity1> all = entity1Dao.findAll();
        System.out.println("all = " + all);
        return all;
    }
}
