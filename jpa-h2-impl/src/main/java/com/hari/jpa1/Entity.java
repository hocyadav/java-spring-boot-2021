package com.hari.jpa1;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 07-03-2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Entity {
    Integer id;

    String name;

    Entity_type entityType;

    BigDecimal priceBigDecimal;

    List<String> list = new ArrayList<>();
}
