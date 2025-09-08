package com.schoolmanagement.auth;

import com.schoolmanagement.commonservice.dto.UserDTO;

public interface UserServiceAdapter {
    UserDTO getUserById(Long userId);
}
