package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassModel, Long> {

    List<ClassModel> findBySchoolId(Long schoolId);

    boolean existsByClassNameAndSectionAndAcademicYearAndSchoolId(
            String className, String section, String academicYear, Long schoolId
    );
}
