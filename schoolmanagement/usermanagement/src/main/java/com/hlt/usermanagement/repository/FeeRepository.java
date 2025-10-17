package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.FeeModel;
import com.hlt.usermanagement.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<FeeModel, Long> {
    Page<FeeModel> findByAcademic(AcademicModel academic, Pageable pageable);
    Page<FeeModel> findByStudent(UserModel student, Pageable pageable);
}
