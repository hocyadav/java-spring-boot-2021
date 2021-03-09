package com.hari.jpa1;

import lombok.*;

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
public class Part {
    String name;
}
