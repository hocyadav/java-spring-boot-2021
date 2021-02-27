package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
@Entity
public class Item extends BaseEntity{
    String name;
    Integer price;
}
