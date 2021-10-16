package io.hari.machinecodingtips.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class MyServiceOldStyle {
    //NOTE IMP point : @Valid : only validate annotation that dont have group added.
    // So if all field have some group name then it will not validate any one
    public void stringMethod(@Valid EntityA entityA) {
        System.out.println("OLD : number validation done : entityA = " + entityA);
    }
}
