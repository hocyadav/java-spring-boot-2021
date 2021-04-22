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
@Table(name = "entity2")
public class Entity2 extends BaseEntity{
    String name;
}
