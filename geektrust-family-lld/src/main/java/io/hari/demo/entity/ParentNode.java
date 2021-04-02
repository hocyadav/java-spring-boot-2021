package io.hari.demo.entity;

import lombok.*;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
//@ToString(exclude = {})
@AllArgsConstructor
@Builder
public class ParentNode {
    Node papa;
    Node mummy;

    @Override
    public String toString() {
        return "ParentNode{" +
                "papa=" + papa.getName() +
                ", mummy=" + mummy.getName() +
                '}';
    }
}
