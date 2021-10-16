package io.hari.machinecodingtips.validation;

import io.hari.machinecodingtips.validation.inteface.NumberValidation;
import io.hari.machinecodingtips.validation.inteface.StringValidation;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class MyServiceNewStyle {//1. add @Validated class level & method level, 2. at method level also add groups name

    //m1
    @Validated({StringValidation.class})
    public void stringMethod(@Valid EntityA entityA) {
        System.out.println("number validation done : entityA = " + entityA);
    }

    @Validated({NumberValidation.class})
    public void numberMethod(@Valid EntityA entityA) {
        System.out.println("string validation done : entityA = " + entityA);
    }

    //m2 : simple filed validation we can do at input level, and other validation delegate to validation_method
    //m2 will be usefule when our entity is already created and we dont want to change it, else we can use above m1 approach
    public void foo(@NotEmpty List<Integer> list, @NotNull String name, @NotNull EntityA entityA) {
        validationSpecificToThisMethod(entityA);
    }

    private void validationSpecificToThisMethod(EntityA entityA) {
        //validation only for foo method, not all fields validation or everything
        //tips : use Objects class
        Objects.requireNonNullElseGet(entityA, () -> new EntityA());
    }
}
