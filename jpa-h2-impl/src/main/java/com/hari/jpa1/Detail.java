package com.hari.jpa1;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 09-03-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
public class Detail {
    String name;
    List<Part> parts = new ArrayList<>();
    int age;
}
