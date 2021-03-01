package com.example.demojava.jpa_set_convertor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demojava.jpa_set_convertor.entity.PhoneNumber;

/**
 * @author HariomYadav
 * @since 28/10/20
 */
public interface PNumDao extends JpaRepository<PhoneNumber, Integer> {}
