package io.hari.machinecodingtips.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class EntityB {
    String name;
    Integer number;
}
