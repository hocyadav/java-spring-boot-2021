package io.hari.demo.service;

import java.util.List;

public interface IBaseService<E> {//optional
    void saveOrUpdate(E entity);
    List<E> findAll();
    E findById(Long id);
    void remove(E entity);
}
