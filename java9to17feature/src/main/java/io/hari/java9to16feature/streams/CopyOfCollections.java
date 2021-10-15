package io.hari.java9to16feature.streams;

import java.util.List;

public class CopyOfCollections {//take input as Collection type
    public static void main(String[] args) {
        List<String> immutableList = List.of("hari", "om", "yadav");//immutable list

        List<String> immutableList2 = List.copyOf(immutableList);//useful when we want no one can change its value
        fun(immutableList2);

        //similar : Set, Map : copyOf() and Of()
    }

    private static void fun(List<String> immutableList2) {
        //try to modify list, but not possible
    }
}
