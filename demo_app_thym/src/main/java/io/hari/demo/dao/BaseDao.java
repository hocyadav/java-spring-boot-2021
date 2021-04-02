package io.hari.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Author Hariom Yadav
 * @create 31-03-2021
 */
@NoRepositoryBean
public interface BaseDao<K> extends JpaRepository<K, Long> {
}
