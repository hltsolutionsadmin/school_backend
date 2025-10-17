package com.hlt.usermanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicUserDTO {
    private Long userId;
    private String fullName;
    private String email;
    private String role;
}
