package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.SchoolCalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SchoolCalendarRepository extends JpaRepository<SchoolCalendarModel, Long> {

    Long countByDateBetweenAndIsInstructionalTrue(LocalDate startDate, LocalDate endDate);
}