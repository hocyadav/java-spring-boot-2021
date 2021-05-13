package io.hari.democonnectors.google_big_query.entity;

import lombok.*;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldG {
    String fieldName;
    String fieldType;
    @Builder.Default
    SubFieldG subFields = new SubFieldG();
}
