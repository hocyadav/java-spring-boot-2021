package io.hari.machinecodingtips.oneentity_onerepo_map.entity;

import lombok.*;

import java.util.Collection;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
@ToString(callSuper = false)
public class WrapperEntity {
    String wrapperName;
    Collection<String> collection;
}
