package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.SchoolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolModel, Long> {
    
    Optional<SchoolModel> findByCode(String code);

    boolean existsByCode(String code);
    
    boolean existsByName(String name);

    boolean existsByCodeAndIdNot(String code, Long id);

    boolean existsByNameAndIdNot(String name, Long id);

}
