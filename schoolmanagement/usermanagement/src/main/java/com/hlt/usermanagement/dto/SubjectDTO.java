package com.hlt.usermanagement.dto;

import lombok.Data;
import java.util.Set;

@Data
public class SubjectDTO {

    private Long id;
    private String name;
    private String code;
    private Long classId;
    private String className;
    private String section;
    private Set<Long> teacherIds;
    private Set<String> teacherNames;
}

