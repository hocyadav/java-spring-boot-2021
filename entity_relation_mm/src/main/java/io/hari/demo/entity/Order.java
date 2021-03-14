package io.hari.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"items"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    String cusId;
    Integer price;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)//m1
//    @JoinColumn(name = "orders_id")//working - this will not change any code change , only change in db side ,
    // remove order_item table and add new column in item table as "orders_id"
    List<Item> items = new ArrayList<>();
}
