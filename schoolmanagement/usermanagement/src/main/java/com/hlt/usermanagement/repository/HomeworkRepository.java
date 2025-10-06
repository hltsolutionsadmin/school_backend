package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.HomeworkModel;
import com.hlt.usermanagement.model.StudentModel;
import com.hlt.usermanagement.model.StaffModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.dto.enums.HomeworkStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkModel, Long> {

    List<HomeworkModel> findByStaff(StaffModel staff);

    List<HomeworkModel> findBySchool(SchoolModel school);

    List<HomeworkModel> findByStatus(HomeworkStatus status);

    List<HomeworkModel> findByDueDateBeforeAndStatus(LocalDate date, HomeworkStatus status);

    List<HomeworkModel> findByStudentAndStatus(StudentModel student, HomeworkStatus status);

    Page<HomeworkModel> findBySchool(SchoolModel school, Pageable pageable);

    Page<HomeworkModel> findByStaff(StaffModel staff, Pageable pageable);

    Page<HomeworkModel> findByStudent(StudentModel student, Pageable pageable);

    Page<HomeworkModel> findByStudentAndStatus(StudentModel student, HomeworkStatus status, Pageable pageable);

    Page<HomeworkModel> findByStatus(HomeworkStatus status, Pageable pageable);
}
