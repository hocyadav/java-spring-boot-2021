package io.hari.demo.entity;

import io.hari.demo.convertor.LocationConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Cab extends BaseEntity{
    String status;

    @Convert(converter = LocationConverter.class)
    Location location;
}
