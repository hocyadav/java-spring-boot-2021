package io.hari.demo.entity;

import lombok.*;

import java.util.Optional;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"spouse"})
@AllArgsConstructor
@Builder
public class Node {
    String name;
    Gender gender;
    ParentNode parentNode = new ParentNode();
    Node spouse;
    ChildrenNode childrenNode;

    public ChildrenNode getChildrenOptional() {
        final ChildrenNode childrenNode = Optional.ofNullable(this.childrenNode).orElseGet(() -> new ChildrenNode());
        return childrenNode;
    }

    public Node getSpouseNodeOptional() {
        final Node node = Optional.ofNullable(this.spouse).orElseGet(() -> new Node());
        return node;
    }

}

