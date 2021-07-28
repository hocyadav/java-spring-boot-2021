package com.hari.jpa1.entity.hexagonal_arch_entity.model_item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelItem {
    Integer id;

    String name;
}
