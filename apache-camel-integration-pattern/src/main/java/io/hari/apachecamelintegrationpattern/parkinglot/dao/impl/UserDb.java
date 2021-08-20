package io.hari.apachecamelintegrationpattern.parkinglot.dao.impl;

import io.hari.apachecamelintegrationpattern.parkinglot.dao.IUserDb;
import io.hari.apachecamelintegrationpattern.parkinglot.entity.Spot;
import io.hari.apachecamelintegrationpattern.parkinglot.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserDb implements IUserDb {
    Map<Integer, User> map = new HashMap<>();

    @Override
    public void create(User user) {
        map.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        map.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        //
    }
}
