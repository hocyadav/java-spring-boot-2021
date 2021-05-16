package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author Hariom Yadav
 * @create 5/16/2021
 */
public class UserDao implements BaseDao<User>{
    private Map<Long, User> USER_DB = new HashMap<>();

    @Override
    public User save(User user) {
        USER_DB.putIfAbsent(user.getId(), user);
        return findById(user.getId());
    }

    @Override
    public User findById(Long id) {
        final User user = Optional.ofNullable(USER_DB.get(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    @Override
    public User update(Long id) {
        final User user = Optional.ofNullable(USER_DB.get(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return save(user);
    }

    @Override
    public void delete(Long id) {
        final User user = Optional.ofNullable(USER_DB.get(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
        USER_DB.remove(user.getId());
    }

    public Map<Long, User> getAll() {
        return USER_DB;
    }
}
