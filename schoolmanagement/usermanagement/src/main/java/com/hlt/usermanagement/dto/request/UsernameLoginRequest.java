package com.hlt.usermanagement.dto.request;

import com.schoolmanagement.commonservice.enums.ERole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UsernameLoginRequest {
    @NotBlank
    private String username;

    private String fullName;

    private String email;

    private String primaryContact;

    @NotBlank
    private String password;

    private Set<ERole> userRoles = new HashSet<>();
}
