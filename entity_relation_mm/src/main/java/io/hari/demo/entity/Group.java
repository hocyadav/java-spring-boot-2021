package io.hari.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"users"})
@AllArgsConstructor
@Builder
@Entity
@Table(name = "groups")
public class Group extends BaseEntity{
    String name;

//    @ManyToMany(fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.EAGER)
    List<User> users = new ArrayList<>();
}
