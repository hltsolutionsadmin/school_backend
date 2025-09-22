package com.hlt.usermanagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDTO {
    private Long id;
    private Long studentId;
    private LocalDate date;
    private Boolean present;
}
