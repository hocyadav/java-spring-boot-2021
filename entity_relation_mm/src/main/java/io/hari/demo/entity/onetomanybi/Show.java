package io.hari.demo.entity.onetomanybi;

import io.hari.demo.entity.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 13-03-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"seats"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Show extends BaseEntity {
//    @Column(length = 20) //varchar 30 , working , error msg --> Value too long for column "NAME VARCHAR(20)": "'123456789012345678901' (21)"; SQL statement:
    @Length(max = 20) //varchar 20 , working , error msg --> 'length must be between 0 and 20'
    String name;

//    @OneToMany(cascade = CascadeType.ALL)//case 1

//    @OneToMany(cascade = CascadeType.ALL)//case2
//    @JoinColumn(name = "seat_id")

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "show")//case3 +  add @ManyToOne(fetch = FetchType.LAZY) Show show;
    List<Seat> seats = new ArrayList<>();

    //other metadata
}
