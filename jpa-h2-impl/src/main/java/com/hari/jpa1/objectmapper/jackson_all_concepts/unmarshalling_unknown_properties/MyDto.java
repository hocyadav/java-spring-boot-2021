package com.hari.jpa1.objectmapper.jackson_all_concepts.unmarshalling_unknown_properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * https://www.baeldung.com/jackson-deserialize-json-unknown-properties
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyDto {
    private String name;
    private int id;
}
