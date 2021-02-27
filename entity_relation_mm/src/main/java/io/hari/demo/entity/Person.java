package io.hari.demo.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"address"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Person extends BaseEntity{
    String name;
    Integer age;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Address address;
}
