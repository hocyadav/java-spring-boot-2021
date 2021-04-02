package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * @Author Hariom Yadav
 * @create 31-03-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Employee extends BaseEntity {
    String name;
    String email;
}
