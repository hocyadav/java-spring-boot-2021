package io.hari.democonnectors.google_big_query.entity;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SchemaField {
    @Builder.Default
    List<FieldG> fields = new LinkedList<>();
}
