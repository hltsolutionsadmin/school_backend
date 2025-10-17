package com.hlt.usermanagement.repository;
import com.hlt.usermanagement.model.ExamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamModel, Long> {

    List<ExamModel> findByAcademic_Id(Long academicId);
}
