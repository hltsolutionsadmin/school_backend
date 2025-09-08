package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username must not exceed 20 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    private String email;

    private String emailHash;

    @NotBlank(message = "Primary contact is required")
    @Size(max = 15, message = "Primary contact must not exceed 15 characters")
    private String primaryContact;

    private String primaryContactHash;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    private String gender;

    private Long profilePictureId;

    private String fcmToken;

    @NotNull(message = "School is required")
    private Long schoolId;

    @NotEmpty(message = "At least one role must be assigned")
    private Set<String> roles;

    @NotNull(message = "Profile completed flag is required")
    private Boolean profileCompleted;

    private List<AddressDTO> addresses;
}
