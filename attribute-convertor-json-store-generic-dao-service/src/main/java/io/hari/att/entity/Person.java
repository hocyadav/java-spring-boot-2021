package io.hari.att.entity;

import io.hari.att.convertor.AddressConvertor;
import io.hari.att.convertor.CryptoConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString(exclude = {"address"})
public class Person extends BaseEntity {
    String name;

    @Convert(converter = AddressConvertor.class)
    Address address;

    @Enumerated(EnumType.STRING)
    AgeType ageType;

    @Past
    LocalDate dob;

    @Transient
    Integer age;

    @Convert(converter = CryptoConverter.class)
    String creditCardNumber;

    public Integer getAge() {
        if (dob != null) {
            final Period period = Period.between(dob, LocalDate.now());
            final int years = period.getYears();
            return years;
        }
        return null;
    }
}
