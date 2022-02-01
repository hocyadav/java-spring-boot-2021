package io.hari.machinecodingtips.lombok;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class MyEntity {
    String name;
    String address;
    Integer phone;
}
