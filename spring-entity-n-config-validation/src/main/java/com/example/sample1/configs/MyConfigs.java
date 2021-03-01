package com.example.sample1.configs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author HariomYadav
 * @since 07/02/21
 */
@Component
@ConfigurationProperties (prefix = "app-name")
@Data
@Validated//2. validate below all validations
public class MyConfigs {
    Map<String, MyClass> allUsers;
    @NotEmpty
    Set<String> newConfig;//we can use set or list

    @NotNull
    Integer newConfig2;

    @Data
    public static class MyClass {
        //1 validation annotaion
        @NotNull @NotEmpty @Size (min = 2, max = 6)
        String name;

        @NotNull
        UserType userType;

        @NotNull @NotEmpty
        List<String> visited;


        List<BigInteger> phoneNumber;

        @NotNull @Min (10) @Max (100)
        Integer decimalTest;

        @DecimalMin(value = "10.0") @Digits(integer = 2, fraction = 2)//working fine
        BigDecimal bigDecimal;
    }
}
