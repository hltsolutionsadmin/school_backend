package com.hlt.usermanagement.repository;
import com.hlt.usermanagement.dto.enums.TaskType;
import com.hlt.usermanagement.model.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {


    Page<TaskModel> findByTaskType(TaskType taskType, Pageable pageable);


    Page<TaskModel> findByAcademic_Id(Long academicId, Pageable pageable);


    Page<TaskModel> findByInitiatedBy_Id(Long initiatedById, Pageable pageable);

    Page<TaskModel> findByAcademic_IdAndTaskDateOrderByCreatedAtDesc(
            Long academicId, LocalDate taskDate, Pageable pageable
    );
    Page<TaskModel> findByAcademic_IdOrderByCreatedAtDesc(
            Long academicId, Pageable pageable
    );
}
