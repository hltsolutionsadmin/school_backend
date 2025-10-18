package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.ExamTimetableModel;
import com.hlt.usermanagement.model.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamTimetableRepository extends JpaRepository<ExamTimetableModel, Long> {

    List<ExamTimetableModel> findByExam_Id(Long examId);

    Optional<ExamTimetableModel> findByExam_IdAndSubject(Long examId, SubjectModel subject);
}
