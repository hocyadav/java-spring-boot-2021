package io.hari.machinecodingtips.design_pattern.filter;

import io.hari.machinecodingtips.design_pattern.filter.model.Student;
import io.hari.machinecodingtips.design_pattern.filter.pattern.AgeFilterCriteria;
import io.hari.machinecodingtips.design_pattern.filter.pattern.And2FilterCriteria;
import io.hari.machinecodingtips.design_pattern.filter.pattern.NameFilterCriteria;
import io.hari.machinecodingtips.design_pattern.filter.pattern.Or2FilterCriteria;

import java.util.Arrays;
import java.util.List;

public class TestFilterDesignPattern {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                Student.builder().name("hari").age(34).build(),
                Student.builder().name("hariom").age(34).build(),
                Student.builder().name("omprakash").age(31).build(),
                Student.builder().name("chandan").age(27).build(),
                Student.builder().name("sanjay").age(30).build(),
                Student.builder().name("rajat").age(27).build()
        );

        // check filter 1
        NameFilterCriteria nameFilter = new NameFilterCriteria();
        List<Student> studentList = nameFilter.checks(students);
        System.out.println("nameFilter = " + studentList);

        // check filter 2
        AgeFilterCriteria ageFilter = new AgeFilterCriteria();
        List<Student> studentList1 = ageFilter.checks(students);
        System.out.println("ageFilter = " + studentList1);

        // check filter 1 & filter 2
        And2FilterCriteria and2Filter = new And2FilterCriteria(nameFilter, ageFilter);//pass filter for and operation
        List<Student> studentList2 = and2Filter.checks(students);
        System.out.println("\nand2Filter = " + studentList2);

        // check filter 1 or filter 2
        Or2FilterCriteria or2Filter = new Or2FilterCriteria(nameFilter, ageFilter);
        List<Student> studentList3 = or2Filter.checks(students);
        System.out.println("\nor2Filter = " + studentList3);
    }
}
