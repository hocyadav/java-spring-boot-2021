package io.hari.att.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EntityAttribute implements Serializable {
    Map<AttributeKey, String>  attributes;
}
