package com.hari.jpa1.objectmapper;

import lombok.*;

import java.io.Serializable;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Order1 {
    String name;
    String email;
    String location;
    String state;
    String city;
    Integer zip;
    String country;
}
