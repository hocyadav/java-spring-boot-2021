package io.hari.demo.dao;

import io.hari.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends BaseDao<User> {
}
