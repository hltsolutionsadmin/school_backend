package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.AttendanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceModel, Long> {

    Page<AttendanceModel> findByStudent_Id(Long studentId, Pageable pageable);

    List<AttendanceModel> findByStudent_ClassModel_IdAndDate(Long classId, LocalDate date);

    AttendanceModel findByStudent_IdAndDate(Long studentId, LocalDate date);

    Long countByStudent_IdAndDateBetweenAndPresentTrue(Long studentId, LocalDate startDate, LocalDate endDate);

}
