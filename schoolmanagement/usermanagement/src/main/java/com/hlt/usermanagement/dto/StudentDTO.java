package com.hlt.usermanagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private Long id;
    private Long userId;
    private String userName;
    private Long schoolId;
    private String schoolName;
    private Long classId;
    private String className;
    private String section;
    private String rollNumber;
    private LocalDate admissionDate;
    private Boolean active;
}
