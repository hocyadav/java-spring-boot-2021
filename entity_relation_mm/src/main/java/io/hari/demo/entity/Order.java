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
public class Order extends BaseEntity{
    String cusId;
    Integer price;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Item> items = new ArrayList<>();
}
