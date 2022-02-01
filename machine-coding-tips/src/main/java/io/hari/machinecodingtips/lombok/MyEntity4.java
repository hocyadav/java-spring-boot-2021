package io.hari.machinecodingtips.lombok;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor(staticName = "of")
public class MyEntity4 {//add both staticName as "of" + builder
    String name;
    String address;
    Integer phone;
}
