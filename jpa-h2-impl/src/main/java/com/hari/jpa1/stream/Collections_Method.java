package com.hari.jpa1.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Hariom Yadav
 * @create 5/22/2021
 */
public class Collections_Method {

    @Test
    public void test() {
        testMap();
        testList();
        testSet();
        collectionsTest();
        collectionSortingSearching();
    }

    public void testMap() {
        final Map<String, String> map = getMap();
        System.out.println("map = " + map);

        final Map<String, String> unmodifiableMap = Collections.unmodifiableMap(map);
//        unmodifiableMap.put("hariom","yadav"); //error
        System.out.println("unmodifiableMap = " + unmodifiableMap);
    }

    public void testList() {
        final List<String> list = getList();
        System.out.println("list = " + list);

        final List<String> unmodifiableList = Collections.unmodifiableList(list);
//        unmodifiableList.add("yadav"); //error
        System.out.println("unmodifiableList = " + unmodifiableList);
    }

    public void testSet() {
        final Set<String> set = getList().stream().collect(Collectors.toSet());
        set.add("chandan");
        System.out.println("set = " + set);

        final Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
//        unmodifiableSet.add("neha"); //error
        System.out.println("unmodifiableSet = " + unmodifiableSet);

    }

    public void collectionsTest() {
        final List<String> emptyList2 = Collections.emptyList();
        System.out.println("emptyList2 = " + emptyList2);

        final List<Object> emptyList1 = Collections.emptyList();
        System.out.println("emptyList1 = " + emptyList1);

        final List<String> emptyList = Collections.EMPTY_LIST;//immutable list
        System.out.println("emptyList = " + emptyList);

        final Map<String, String> emptyMap = Collections.EMPTY_MAP;//immutable map
//        emptyMap.put("hari..", "om..");//error we cant add
        System.out.println("emptyMap = " + emptyMap);

        final Map<String, String> emptyMap1 = Collections.emptyMap();//immutable map
//        emptyMap1.put("hariom", "yadav");//error
        System.out.println("emptyMap1 = " + emptyMap1);

        final Set emptySet = Collections.EMPTY_SET;
        System.out.println("emptySet = " + emptySet);

        final List<String> stringList = getList();
        System.out.println("stringList simple= " + stringList);

        Collections.reverse(stringList);
        System.out.println("stringList reverse= " + stringList);

        Collections.shuffle(stringList);
        System.out.println("stringList shuffle= " + stringList);

        final Map<String, String> singletonMap = Collections.singletonMap("hari", "om");//immutable + singleton
//        singletonMap.put("chandan", "yadav");//error
        System.out.println("singletonMap = " + singletonMap);
    }

    public void collectionSortingSearching() {
        final List<String> list = getList();
        final int index = Collections.binarySearch(list, "hari");
        System.out.println("index = " + index);
        System.out.println("list.get(index) = " + list.get(index));

        final int index2 = Collections.binarySearch(list, "om");
        System.out.println("index2 = " + index2);
        System.out.println("list.get(index) = " + list.get(index2));

        final int index3 = Collections.binarySearch(list, "yadav");
        System.out.println("index3 = " + index3);
        System.out.println("list.get(index) = " + list.get(index3));

        final int chandanIndex = Collections.binarySearch(list, "chandan");
        System.out.println("chandanIndex = " + chandanIndex);
        //m1
        Optional.ofNullable(chandanIndex)
                .filter(i -> i >= 0)
                .ifPresent(index_ -> {
                    System.out.println("list = " + list.get(index_));
                });
        //m2
//        final Integer chandanIndex_ = Optional.ofNullable(chandanIndex)
//                .filter(i -> i >= 0).orElseThrow(() -> new RuntimeException("-1 index"));
//        System.out.println("list = " + list.get(chandanIndex_));

        final List<String> sourceList = getList();
        List<String> destinationList = Arrays.asList(new String[sourceList.size()]);//in destination List size required : set size same as source list, https://stackoverflow.com/questions/5207162/define-a-fixed-size-list-in-java
        Collections.copy(destinationList, sourceList);
        System.out.println("sourceList = " + sourceList);
        System.out.println("destinationList = " + destinationList);

//        List<String> list1 = new LinkedList<>();
        List<String> list1 = Arrays.asList(new String[sourceList.size()]);
        Collections.fill(list1, "hariom");//if input list is empty then it will not fill anything, so set size first
        System.out.println("list1 fill = " + list1);

        final List<String> list_1 = getList();
        final List<String> list_2 = Arrays.asList("chandan", "neha", "omp");
        System.out.println("list_2 = " + list_2);
        final boolean disjoint = Collections.disjoint(list_1, list_2);//if both list are different then TRUE, i.e. intersection is empty
        System.out.println("disjoint = " + disjoint);

        List<String> stringList = Arrays.asList("hari", "hari", "om", "om", "om");
        final int frequency = Collections.frequency(stringList, "hari");
        System.out.println("frequency = " + frequency);
        System.out.println("frequency = " + Collections.frequency(stringList, "om"));

        final Enumeration<String> emptyEnumeration = Collections.emptyEnumeration();
        System.out.println("emptyEnumeration = " + emptyEnumeration);
    }

    private Map<String, String> getMap() {
        final Map<String, String> map = new HashMap<>();
        map.put("hari", "om");
        map.put("hari2", "om");
        return map;
    }

    private List<String> getList() {
        return Arrays.asList("hari", "om", "yadav");
    }
}
