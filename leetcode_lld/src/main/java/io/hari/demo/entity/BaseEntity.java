package io.hari.demo.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@MappedSuperclass
@Getter
@ToString
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
