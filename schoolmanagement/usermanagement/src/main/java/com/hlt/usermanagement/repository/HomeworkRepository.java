package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.HomeworkModel;
import com.hlt.usermanagement.model.StaffModel;
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


    List<HomeworkModel> findByStatus(HomeworkStatus status);

    List<HomeworkModel> findByDueDateBeforeAndStatus(LocalDate date, HomeworkStatus status);



    Page<HomeworkModel> findByStatus(HomeworkStatus status, Pageable pageable);
}
