package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.TeacherModel;
import com.hlt.usermanagement.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {


    @Query("SELECT t FROM TeacherModel t LEFT JOIN FETCH t.classInCharge WHERE t.id = :id")
    Optional<TeacherModel> findByIdWithClassInCharge(@Param("id") Long id);

    Optional<TeacherModel> findByUser(UserModel user);

    Page<TeacherModel> findBySchool_Id(Long schoolId, Pageable pageable);

    boolean existsByUser(UserModel user);
}
