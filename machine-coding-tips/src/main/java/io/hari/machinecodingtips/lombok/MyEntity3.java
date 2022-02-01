package io.hari.machinecodingtips.lombok;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor
public class MyEntity3 {
    String name;
    String address;
    Integer phone;
}
