package io.hari.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"userQuestions", "subscribeContests", "userContestsSolution", "notifications"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    String name;

    @ManyToMany(cascade = CascadeType.ALL)
    List<UserQuestion> userQuestions;

    Integer totalScore;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Contest> subscribeContests;

    @ManyToMany(cascade = CascadeType.ALL)
    List<UserContest> userContestsSolution;

    @OneToMany(cascade = CascadeType.ALL)
    List<Notification> notifications;
}
