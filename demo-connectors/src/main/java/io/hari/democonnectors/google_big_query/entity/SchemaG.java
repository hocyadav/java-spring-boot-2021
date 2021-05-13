package io.hari.democonnectors.google_big_query.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
@Data
@Builder
@ToString
@AllArgsConstructor
public class SchemaG {
    String schemaName;

    @Builder.Default
    SchemaField schemaField = new SchemaField();
}
