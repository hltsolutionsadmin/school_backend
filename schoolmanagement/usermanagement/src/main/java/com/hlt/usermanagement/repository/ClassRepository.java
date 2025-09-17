package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassModel, Long> {
    List<ClassModel> findBySchoolId(Long schoolId);
    boolean existsByClassNameAndSectionAndSchoolId(String className, String section, Long schoolId);
}
