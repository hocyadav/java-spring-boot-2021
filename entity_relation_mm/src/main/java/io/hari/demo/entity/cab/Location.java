package io.hari.demo.entity.cab;

import io.hari.demo.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

/**
 * @Author Hariom Yadav
 * @create 24-03-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Location extends BaseEntity {
    Double x;
    Double y;
}
