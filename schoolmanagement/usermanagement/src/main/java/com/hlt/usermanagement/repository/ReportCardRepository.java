package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.ReportCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReportCardRepository extends JpaRepository<ReportCardModel, Long> {

    List<ReportCardModel> findByAcademic_IdAndStudentName(Long academicId, String studentName);

    Optional<ReportCardModel> findByResult_Id(Long resultId);
}
