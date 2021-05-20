package com.example.sample1.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author Hariom Yadav
 * @create 5/20/2021
 * https://docs.jboss.org/hibernate/validator/4.2/reference/en-US/html_single/#validator-defineconstraints-spec
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "app-validation")
@Validated//imp - this makes validate below all hibernate validation
public class ConfigClassValidation {
    @AssertTrue
    Boolean boolean1;

    //@AssertTrue//error message
    @AssertFalse
    Boolean boolean2;

    Boolean boolean3;

    @DecimalMax(value = "100")
    BigInteger decimalMax;

    @DecimalMin(value = "200.123")
//    @DecimalMin(value = "200.123", inclusive = false)//dont include 200.123 value
    BigDecimal decimalMin;

    @Digits(integer = 3, fraction = 2) //working inter part length max 3, and fraction part length 2
    BigDecimal digit;

    //min value start from 10.10 with inclusive + integer and fraction value length 2 and
    @DecimalMin(value = "10.10")
    @Digits(integer = 2, fraction = 2) //working inter part length max 3, and fraction part length 2
    BigDecimal digit2;
}

