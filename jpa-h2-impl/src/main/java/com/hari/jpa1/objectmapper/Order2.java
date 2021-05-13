package com.hari.jpa1.objectmapper;

import lombok.*;

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
public class Order2 {
    String name;
    String email;
    Integer zip;
    String country;
}
