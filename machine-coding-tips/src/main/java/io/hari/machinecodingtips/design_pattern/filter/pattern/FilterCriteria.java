package io.hari.machinecodingtips.design_pattern.filter.pattern;

import io.hari.machinecodingtips.design_pattern.filter.model.Student;

import java.util.List;

public interface FilterCriteria { List<Student> checks(List<Student> list); }
