package io.hari.stackoverflow.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question extends BaseEntity{
    String question;
    List<QuestionType> type;
    @OneToOne
    Answer answer;
    Integer count;
    //metadata
}
