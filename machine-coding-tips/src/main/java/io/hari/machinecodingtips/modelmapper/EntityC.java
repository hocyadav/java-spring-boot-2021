package io.hari.machinecodingtips.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class EntityC {
    String fullName;
    Integer fullNumber;
}
