package com.hari.jpa1.entity;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * @Author hayadav
 * @create 4/25/2021
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    String street;
    Integer pin;
}
