package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
@Entity
public class Address extends BaseEntity{
    String line1;
    @Column(unique = true)//makes 2 way
    Integer pincode;
}
