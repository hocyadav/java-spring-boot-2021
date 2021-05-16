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
    private Map<Long, User> userMap = new HashMap<>();

    @Override
    public User save(User user) {
        return userMap.putIfAbsent(user.getId(), user);
    }

    @Override
    public User findById(Long id) {
        validateUser(id);
        return userMap.get(id);
    }

    @Override
    public User update(User user) {
        validateUser(user.getId());
        return save(user);
    }

    @Override
    public void delete(Long id) {
        validateUser(id);
        userMap.remove(id);
    }

    public Map<Long, User> getAll() {
        return userMap;
    }

    private void validateUser(Long id) {
        Optional.ofNullable(userMap.get(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
