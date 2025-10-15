package com.hlt.usermanagement.dto;

import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class AcademicUnitDTO {

    private Long id;

    private String className;
    private String section;
    private String academicYear;
    private Long schoolId;
    private String schoolName;
    private Long classTeacherId;
    private String classTeacherName;
    private Integer capacity;
    private String roomNumber;
    private Boolean isActive;
    private String timetable;
    private String notes;

    private List<Long> studentIds;
    private Set<Long> subjectIds;
}
