package com.hlt.usermanagement.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;
    private Long studentId;
    private String subject;
    private String grade;
    private Double score;
    private String remarks;
}
