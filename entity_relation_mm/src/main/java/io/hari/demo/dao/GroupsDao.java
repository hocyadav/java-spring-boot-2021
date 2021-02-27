package io.hari.demo.dao;

import io.hari.demo.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsDao extends BaseDao<Group> {
    List<Group> findAllByUsers_Id(Long id);
}
