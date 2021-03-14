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
public class Group extends BaseEntity {
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "users_id")//not working here, try with one to many
//    @OneToMany(fetch = FetchType.EAGER)
    List<User> users = new ArrayList<>();
}
