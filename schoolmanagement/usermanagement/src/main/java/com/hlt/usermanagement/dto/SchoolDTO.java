package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolDTO {

    private Long id;

    @NotBlank(message = "School name cannot be blank")
    @Size(max = 100, message = "School name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "School code cannot be blank")
    @Size(max = 20, message = "School code cannot exceed 20 characters")
    private String code;

    @Size(max = 20, message = "Contact number cannot exceed 20 characters")
    private String contactNumber;

    @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;

    private Long adminId;

    private String adminName;

    private AddressDTO address;
}
