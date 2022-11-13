package io.hari.machinecodingtips.design_pattern.filter.pattern;

import io.hari.machinecodingtips.design_pattern.filter.model.Student;

import java.util.List;

public class And2FilterCriteria implements FilterCriteria {

    private final FilterCriteria[] filterCriteria;

    public And2FilterCriteria(FilterCriteria... filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    @Override
    public List<Student> checks(List<Student> list) {
       List<Student> students = list;
       for (FilterCriteria filterCriteria : this.filterCriteria) {
           List<Student> response = filterCriteria.checks(students);
           students = response;
       }
       return students;
    }
}
