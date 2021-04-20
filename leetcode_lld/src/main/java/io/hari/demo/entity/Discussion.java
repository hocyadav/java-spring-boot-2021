package io.hari.demo.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"replies"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discussions")
public class Discussion extends BaseEntity{
    String title;
    String body;

    @OneToMany(cascade = CascadeType.ALL)
    List<Reply> replies;

    public List<String> getAllReply() {
        return replies.stream().map(i -> i.getReply()).collect(Collectors.toList());
    }
}
