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
@ToString(exclude = {"testCases", "discussions"}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "questions")
public class Question extends BaseEntity{
    String question;
    QuestionStatus status;
    Integer totalScore;
    String codeSubmission;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<TestCase> testCases;

    @OneToMany
    List<Discussion> discussions;
}
