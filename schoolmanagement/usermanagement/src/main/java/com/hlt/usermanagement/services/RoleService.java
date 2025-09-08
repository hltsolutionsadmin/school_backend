package com.hlt.usermanagement.services;


import com.schoolmanagement.commonservice.enums.ERole;
import com.hlt.usermanagement.model.RoleModel;

public interface RoleService {
    RoleModel findByErole(ERole eRole);
}
