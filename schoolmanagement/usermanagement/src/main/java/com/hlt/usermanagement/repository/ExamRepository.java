package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.ExamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamModel, Long> {
    List<ExamModel> findByClassModel_Id(Long classId);
    List<ExamModel> findBySubjectModel_Id(Long subjectId);
    List<ExamModel> findByExamType_Id(Long examTypeId);
}
