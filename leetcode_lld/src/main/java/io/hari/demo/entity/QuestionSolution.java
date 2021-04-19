package io.hari.demo.entity;

import lombok.*;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class QuestionSolution {
    Long questionId;
    String myCode;
}
