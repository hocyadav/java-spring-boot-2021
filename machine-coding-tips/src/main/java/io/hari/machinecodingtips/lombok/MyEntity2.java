package io.hari.machinecodingtips.lombok;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class MyEntity2 {
    String name;
    String address;
    Integer phone;
}
