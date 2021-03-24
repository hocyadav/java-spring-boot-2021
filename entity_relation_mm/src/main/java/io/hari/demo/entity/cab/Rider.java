package io.hari.demo.entity.cab;

import io.hari.demo.entity.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
public class Rider extends BaseEntity {
    String name;

    @OneToOne(cascade = CascadeType.ALL)
    Location location;
}
