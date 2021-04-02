package io.hari.demo.entity;

import io.hari.demo.convertor.LocationConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "users")
public class User extends BaseEntity{
    String name;

    @Convert(converter = LocationConverter.class)
    Location location;
}
