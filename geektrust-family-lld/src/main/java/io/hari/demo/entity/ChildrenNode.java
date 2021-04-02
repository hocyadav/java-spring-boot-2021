package io.hari.demo.entity;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildrenNode {
    List<Node> children = new LinkedList<>();
}
