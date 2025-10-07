package com.hlt.usermanagement.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalDetailsDTO {

    private String fatherName;
    private String motherName;
    private LocalDate dateOfBirth;
    private int age;
    private String bloodGroup;
    private String mobileNumber;
}