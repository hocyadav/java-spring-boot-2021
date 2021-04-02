package io.hari.demo.service;

import io.hari.demo.entity.ChildrenNode;
import io.hari.demo.entity.Gender;
import io.hari.demo.entity.Node;
import io.hari.demo.entity.ParentNode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
public class FamilyTreeDS {
    public Node root;

    public void levelOrderTraversal() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        while (!q.isEmpty()) {
            System.out.println("level " + level);
            final int size = q.size();
            for (int i = 0; i < size; i++) {
                final Node curr = q.poll();
                printSpouseNodeDetails(curr);
                final List<Node> children = curr.getChildrenOptional().getChildren();
                for (Node c : children) q.add(c);
            }
            level++;
        }
        System.out.println();
    }

    private void printSpouseNodeDetails(Node curr) {
        System.out.print(curr.getName().toUpperCase() + " ");
        if (curr.getSpouse() != null) {
            System.out.print("spouse " + curr.getSpouse().getName().toUpperCase());
        }
        if (curr.getSpouse() != null && curr.getChildrenNode() != null) {
            final ChildrenNode childrenNode = curr.getChildrenNode();
            System.out.print(" child : " + childrenNode.getChildren());
        }
        System.out.println();
    }

    //working
    public void searchNodeAndAddChildren(Node node, List<Node> newChildren) {
        final Node nodeByName = searchNodeByName(node.getName());
        if (nodeByName == null) {
            System.out.println("Input Node not found");
            return;
        }
        if (nodeByName.getGender().equals(Gender.male)) {
            addChildrenToPapa(nodeByName, newChildren);
        } else {
            addChildrenToMummy(nodeByName, newChildren);
        }
    }

    public void addChildrenToMummy(Node mummy, List<Node> newChildren) {
        addChildrenToPapa(mummy.getSpouse(), newChildren);
    }

    public void addChildrenToPapa(Node papa, List<Node> newChildren) {
        final ChildrenNode fetchedChildren = papa.getChildrenOptional();
        fetchedChildren.getChildren().addAll(newChildren);
        final ChildrenNode childrenNode = ChildrenNode.builder().children(fetchedChildren.getChildren()).build();
        papa.setChildrenNode(childrenNode);
        papa.getSpouseNodeOptional().setChildrenNode(childrenNode);
        setPapaMummyToAllChildren(papa, fetchedChildren.getChildren());
    }

    private void setPapaMummyToAllChildren(Node papa, List<Node> fetchedChildren) {
        fetchedChildren.forEach(c -> c.setParentNode(ParentNode.builder().papa(papa).mummy(papa.getSpouse()).build()));
    }

    public void addSpouse(Node node, Node spouse) {
        node.setSpouse(spouse);
        spouse.setSpouse(node);
        spouse.setChildrenNode(node.getChildrenOptional());
    }

    public Node searchNodeByName(String name) {
        if (name == root.getName()) return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            final int size = q.size();
            for (int i = 0; i < size; i++) {
                final Node curr = q.poll();
                if (curr.getName().equals(name)) return curr;
                final List<Node> children = curr.getChildrenOptional().getChildren();
                if (curr.getSpouse() != null && curr.getSpouse().getName().equals(name)) //not connected graph
                    return curr.getSpouse();
                for (Node child : children) {
                    if (child.getName().equals(name)) return child;
                    q.add(child);
                }
            }
        }
        return null;
    }

    public Node searchNode(Node searchNode) {
        return searchNodeByName(searchNode.getName());
    }

    public ParentNode findParentNode_(Node searchNode) {
        final Node node = searchNode(searchNode);
        if (node != null) return node.getParentNode();
        return null;
    }

//    public ParentNode findParentNode(Node searchNode) {
//        if (searchNode == root) return root.getParentNode();
//        Queue<Node> q = new LinkedList<>();
//        q.add(root);
//
//        while (!q.isEmpty()) {
//            final int size = q.size();
//            for (int i = 0; i < size; i++) {
//                final Node curr = q.poll();
//                if (curr == searchNode) return curr.getParentNode();
//
//                final List<Node> children = curr.getChildrenOptional().getChildren();
//                for (Node child : children) {
//                    if (child == searchNode) return child.getParentNode();
//                    q.add(child);
//                }
//            }
//        }
//        return null;
//    }

    //working
    public void searchParentAndAddChildren(Node childrenNode, List<Node> newSiblings) {
        final ParentNode searchParentNode = findParentNode_(childrenNode);
        if (searchParentNode != null) {
            addChildrenToPapa(searchParentNode.getPapa(), newSiblings);
        } else {
            System.out.println("Parent Node not found");
        }
    }

//    public ParentNode searchParentAndAddChildren(String name, List<Node> newChildren) {
//        final Node node = searchNodeByName(name);
//        if (node.getParentNode() != null) {
//            final ParentNode parentNode = node.getParentNode();
//            return parentNode;
//        }
//        return null;
//    }
}
