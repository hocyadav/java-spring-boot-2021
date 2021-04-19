package io.hari.demo.entity;

import lombok.*;

import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContestQuestionSolution {
    List<QuestionSolution> questionSolutions;
}
