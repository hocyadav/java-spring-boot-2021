package io.hari.machinecodingtips.lombok;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor(staticName = "create")
public class MyEntity5 {//add both staticName as "create" + builder
    String name;
    String address;
    Integer phone;
}
