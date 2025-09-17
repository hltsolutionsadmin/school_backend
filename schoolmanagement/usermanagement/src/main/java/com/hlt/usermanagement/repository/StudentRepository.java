package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.StudentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
    Page<StudentModel> findByClassModelId(Long classId, Pageable pageable);
    List<StudentModel> findBySchoolId(Long schoolId);
}
