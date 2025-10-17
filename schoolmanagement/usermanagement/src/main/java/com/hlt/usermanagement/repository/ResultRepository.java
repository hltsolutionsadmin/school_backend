package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.ResultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<ResultModel, Long> {

    List<ResultModel> findByExam_Id(Long examId);

    List<ResultModel> findByStudent_Id(Long studentId);

    Optional<ResultModel> findByExam_IdAndStudent_Id(Long examId, Long studentId);
}
