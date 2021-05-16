package io.hari.demo.service;

import io.hari.demo.dao.UserDao;
import io.hari.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;


    public User create(User user) {
        user.setScore(BigInteger.valueOf(1500));
        return userDao.save(user);
    }

    public List<User> createMultiUsers(List<User> users) {
        users.forEach(user -> {
            user.setScore(BigInteger.valueOf(1500));
        });
        return userDao.saveAll(users);
    }

    private boolean validateUserName(String username) {
        if (userDao.findAllByUsername(username).get(0) != null) {//found
            return true;
        }
        return false;
    }
}
