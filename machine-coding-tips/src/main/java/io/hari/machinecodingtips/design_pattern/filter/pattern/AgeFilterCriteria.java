package io.hari.machinecodingtips.design_pattern.filter.pattern;

import io.hari.machinecodingtips.design_pattern.filter.model.Student;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AgeFilterCriteria implements FilterCriteria {
    @Override
    public List<Student> checks(List<Student> list) {
        return list.stream()
                .filter(student -> student.getAge() > 30)
                .collect(Collectors.toList());
    }
}
