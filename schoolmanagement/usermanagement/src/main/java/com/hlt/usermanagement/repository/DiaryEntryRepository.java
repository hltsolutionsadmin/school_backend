package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.DiaryEntryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntryModel, Long> {
}
