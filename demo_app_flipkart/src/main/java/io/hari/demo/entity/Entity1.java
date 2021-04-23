package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {}, callSuper = true)
@Builder
@Entity
@Table(name = "entity1")
public class Entity1 extends BaseEntity{
    String name;
}
