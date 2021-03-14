package io.hari.demo.entity.onetomanybi;

import io.hari.demo.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @Author Hariom Yadav
 * @create 13-03-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"show"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Seat extends BaseEntity {
    String type;

//    @ManyToOne(fetch = FetchType.LAZY)//working
    @ManyToOne(fetch = FetchType.EAGER)//working
    Show show;

    //other metadata
}
