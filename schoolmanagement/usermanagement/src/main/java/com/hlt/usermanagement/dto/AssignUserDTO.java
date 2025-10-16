package com.hlt.usermanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssignUserDTO {
    private List<Long> userIds;
    private String role;
}
