package io.hari.att.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Version
    Long version;

    LocalDateTime createAt;

    @PrePersist
    public void foo() {
          if(createAt == null) {
              createAt = LocalDateTime.now();
          }
    }
}
