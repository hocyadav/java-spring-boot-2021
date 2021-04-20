package io.hari.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class TestCase extends BaseEntity{
    String actualValue;
    String expectedValue;
    Integer score;

    @JsonIgnore
    public int calculateScore(String userSubmittedCode) {
        if (expectedValue.equals(userSubmittedCode)) return score;
        else return 0;
    }
}
