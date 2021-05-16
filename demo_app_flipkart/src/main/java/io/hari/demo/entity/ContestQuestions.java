package io.hari.demo.entity;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author hayadav
 * @create 4/24/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContestQuestions {
    List<Long> questions = new LinkedList<>();
}
