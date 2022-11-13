package io.hari.machinecodingtips.design_pattern.filter.pattern;

import io.hari.machinecodingtips.design_pattern.filter.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class NameFilterCriteria implements FilterCriteria {
    @Override
    public List<Student> checks(List<Student> list) {
        return list.stream()
                .filter(student -> student.getName().contains("om"))
                .collect(Collectors.toList());
    }
}
