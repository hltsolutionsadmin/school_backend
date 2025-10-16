package com.hlt.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicUnitRepository extends JpaRepository<AcademicUnitModel, Long> {


    Optional<AcademicUnitModel> findByName(String name);

}
