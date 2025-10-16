package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.HomeworkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkModel, Long> {

}
