package com.example.demojava.apachecommon;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;

/**
 * @author HariomYadav
 * @since 09/11/20
 * https://www.tutorialspoint.com/commons_collections/commons_collections_intersection.htm
 * https://www.baeldung.com/apache-commons-collection-utils
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(40, 1, 2, 3, 4, 4);
        List<Integer> list2 = Arrays.asList(40, 4, 4, 5, 6, 7);
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        //m1
        final List<Integer> intersection = ListUtils.intersection(list1, list2);//1st : list is not null, 2nd return common elements
        System.out.println("intersection = " + intersection);
        //m2
        final Collection<Integer> intersection1 = CollectionUtils.intersection(list1, list2);// -- "-- + return in sorted order
        System.out.println("intersection1 = " + intersection1);

        final List<Integer> union1 = ListUtils.union(list1, list2);// list1 + list2 as it is.
        System.out.println("union1 = " + union1);

        final Collection<Integer> union = CollectionUtils.union(list1, list2);// common things + sorted order
        System.out.println("union = " + union);

        final Collection<Integer> subtract = CollectionUtils.subtract(Arrays.asList(1, 2, 3, 100, 200), Arrays.asList(1, 2, 3, 4, 5, 6));//element in list1 but not in list2 , simple subtraction
        System.out.println("subtract = " + subtract);

        final boolean subCollection = CollectionUtils.isSubCollection(Arrays.asList(1,2,3), Arrays.asList(1,2,3,4,5));//if 1st list3 is subset of list4 then return true, i.e. list4 is super set
        System.out.println("subCollection = " + subCollection);

        //check for null OR empty
        final boolean empty = CollectionUtils.isEmpty(list1);//value present in list
        System.out.println("empty = " + empty);
        final boolean empty2 = CollectionUtils.isEmpty(null);//null
        System.out.println("empty2 = " + empty2);
        final boolean empty3 = CollectionUtils.isEmpty(Arrays.asList());//empty
        System.out.println("empty3 = " + empty3);

        //check for not empty
        final boolean notEmpty = CollectionUtils.isNotEmpty(list1);//value present
        System.out.println("notEmpty = " + notEmpty);

        //??
        final boolean filter = CollectionUtils.filter(Arrays.asList(1, 2, 3, 4, 5, 6), new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer integer) {
                return true;
            }
        });
        System.out.println("filter = " + filter);

    }
}
