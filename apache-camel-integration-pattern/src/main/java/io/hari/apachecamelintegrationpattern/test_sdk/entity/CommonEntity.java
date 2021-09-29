package io.hari.apachecamelintegrationpattern.test_sdk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
abstract class CommonEntity {
    String uniqueId;
}
