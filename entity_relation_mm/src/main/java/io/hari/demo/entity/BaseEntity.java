package io.hari.demo.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @Version
//    Integer version;
//
//    LocalDateTime updatedAt;
//
//    @PostPersist
//    public void afterPersist() {
//        updatedAt = LocalDateTime.now();
//    }
//
//    @PostUpdate
//    public void afterUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
}
