package entity;

import lombok.*;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity {
    String name;
    String address;
}
