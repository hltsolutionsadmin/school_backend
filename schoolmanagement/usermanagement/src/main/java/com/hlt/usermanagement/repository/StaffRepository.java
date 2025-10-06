package com.hlt.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlt.usermanagement.model.StaffModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.dto.enums.StaffType;

@Repository
public interface StaffRepository extends JpaRepository<StaffModel, Long> {

    Optional<StaffModel> findByUser(UserModel user);

}
