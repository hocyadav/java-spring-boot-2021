package io.hari.att.entity;

import io.hari.att.convertor.AddressConvertor;
import io.hari.att.convertor.CryptoConverter;
import io.hari.att.convertor.MyJsonAttributeConvertor;
import io.hari.att.convertor.MyJsonConvertor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
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

    @Convert(converter = MyJsonConvertor.class)
    JsonEntity jsonEntity;

    @Convert(converter = MyJsonAttributeConvertor.class)
    EntityAttribute attribute;

    public Integer getAge() {
        if (dob != null) {
            final Period period = Period.between(dob, LocalDate.now());
            final int years = period.getYears();
            return years;
        }
        return null;
    }
}
