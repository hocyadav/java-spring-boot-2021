package io.hari.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"questions"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contests")
public class Contest extends BaseEntity{
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Question> questions;
    LocalDateTime starDateTime;
    LocalDateTime endDateTime;

    public List<String> getAllQuestions() {
        return questions.stream().filter(Objects::nonNull).map(i -> i.getQuestion()).collect(Collectors.toList());
    }
}
