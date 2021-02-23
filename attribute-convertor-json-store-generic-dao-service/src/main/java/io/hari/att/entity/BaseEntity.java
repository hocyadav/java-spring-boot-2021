package io.hari.att.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Version
    Long version;
}
