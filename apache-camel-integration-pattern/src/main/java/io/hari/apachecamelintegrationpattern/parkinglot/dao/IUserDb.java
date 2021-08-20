package io.hari.apachecamelintegrationpattern.parkinglot.dao;

import io.hari.apachecamelintegrationpattern.parkinglot.entity.User;

public interface IUserDb {
    void create(User user);

    void update(User user);

    void delete(User user);
}
