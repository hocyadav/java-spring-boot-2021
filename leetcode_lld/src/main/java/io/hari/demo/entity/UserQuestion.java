package io.hari.demo.entity;

import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

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
public class UserQuestion extends BaseEntity{
    @Convert(converter = QuestionSoltionConverter.class)
    QuestionSolution questionSolution;
}
