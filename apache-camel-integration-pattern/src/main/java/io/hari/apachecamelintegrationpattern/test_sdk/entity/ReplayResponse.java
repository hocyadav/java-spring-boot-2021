package io.hari.apachecamelintegrationpattern.test_sdk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Data
@SuperBuilder @NoArgsConstructor @AllArgsConstructor
public class ReplayResponse extends CommonEntity{
    @Builder.Default
    Integer type = 3;
//    String uniqueId;
    @Builder.Default
    Map<String, Object> map = new HashMap<>();
    Object payload;
}
