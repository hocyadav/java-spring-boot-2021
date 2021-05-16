package com.example.demo.dao;

/**
 * @Author Hariom Yadav
 * @create 5/16/2021
 */
public interface BaseDao<T> {
    T save(T entity);
    T findById(Long id);
    T update(T entity);
    void delete(Long id);
}
