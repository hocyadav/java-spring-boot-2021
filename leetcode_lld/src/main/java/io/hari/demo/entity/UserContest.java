package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {}, callSuper = true)
@Entity
public class UserContest extends BaseEntity{
    Long contestId;

    @Convert(converter = ContestQuestionSolutionConverter.class)
    ContestQuestionSolution questionSolution;
}
