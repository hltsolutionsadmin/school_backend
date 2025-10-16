package com.schoolmanagement.commonservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schoolmanagement.commonservice.enums.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class BasicOnboardUserDTO {

    @Size(max = 20)
    @NotBlank(message = "Username is mandatory")
    private final String username;

    @Email(message = "Invalid email format")
    private final String email;

    @NotBlank(message = "Full name is mandatory")
    private final String fullName;

    @NotBlank(message = "Primary contact is mandatory")
    private final String primaryContact;

    private final Set<ERole> userRoles;

    private final Long b2bUnitId;

    @NotBlank(message = "Password is mandatory")
    private final String password;

    @JsonCreator
    public BasicOnboardUserDTO(
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("fullName") String fullName,
            @JsonProperty("primaryContact") String primaryContact,
            @JsonProperty("userRoles") Set<ERole> userRoles,
            @JsonProperty("schoolId") Long b2bUnitId,
            @JsonProperty("password") String password) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.primaryContact = primaryContact;
        this.userRoles = userRoles;
        this.b2bUnitId = b2bUnitId;
        this.password = password;
    }
}
