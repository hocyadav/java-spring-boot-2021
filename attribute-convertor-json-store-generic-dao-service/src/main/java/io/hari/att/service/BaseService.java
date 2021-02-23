package io.hari.att.service;

import io.hari.att.dao.BaseDao;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {
    BaseDao<T> dao;

    public BaseService(BaseDao<T> dao) {
        this.dao = dao;
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public  T save(T s) {
        return dao.save(s);
    }

    public T findById(Long id) {
        final Optional<T> optional = dao.findById(id);
        return optional.get();
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
