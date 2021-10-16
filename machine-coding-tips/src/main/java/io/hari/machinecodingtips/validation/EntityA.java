package io.hari.machinecodingtips.validation;

import io.hari.machinecodingtips.validation.inteface.NumberValidation;
import io.hari.machinecodingtips.validation.inteface.StringValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityA {
    @NotBlank(groups = {StringValidation.class})//then it will be not validated by @valid only also we need to add @validated group name, see ServiceNew impl
    String name;

    @NotNull(groups = {NumberValidation.class})
    BigInteger phoneNumber;

    @Past
    LocalDate dateOfBirth;

    @Email(groups = {StringValidation.class})
    String email;
}
