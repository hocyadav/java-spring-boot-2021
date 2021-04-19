package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discussions")
public class Discussion extends BaseEntity{
    String title;
    String body;
    @OneToMany
    List<Reply> replies;
}
