package com.hari.jpa1.entity.hexagonal_arch_entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelDto {
    Integer id;

    String name;
}
