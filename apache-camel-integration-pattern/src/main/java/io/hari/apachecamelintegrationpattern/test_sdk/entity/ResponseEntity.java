package io.hari.apachecamelintegrationpattern.test_sdk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity extends CommonEntity{
    @Builder.Default
    Integer type = 2;
//    String uniqueId;
    @Builder.Default
    Map<String, Object> map = new HashMap<>();
    Object payload;
}
