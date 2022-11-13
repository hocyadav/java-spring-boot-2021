package io.hari.machinecodingtips.design_pattern.filter.pattern;

import io.hari.machinecodingtips.design_pattern.filter.model.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Or2FilterCriteria implements FilterCriteria {
    private final FilterCriteria[] filterCriteria;

    public Or2FilterCriteria(FilterCriteria... filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    @Override
    public List<Student> checks(List<Student> list) {
        Set<Student> students = new HashSet<>();
        for (FilterCriteria filterCriteria : this.filterCriteria) {
            List<Student> response = filterCriteria.checks(list);
            students.addAll(response);
        }
        return students.stream().toList();
    }
}
