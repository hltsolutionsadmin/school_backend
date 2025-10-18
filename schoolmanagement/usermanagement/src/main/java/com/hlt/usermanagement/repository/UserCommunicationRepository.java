package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.UserCommunicationModel;
import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommunicationRepository extends JpaRepository<UserCommunicationModel, Long> {

    Page<UserCommunicationModel> findByCreatedById(Long userId, Pageable pageable);

    Page<UserCommunicationModel> findByTypeAndStatus(CommunicationType type,
                                                     CommunicationStatus status,
                                                     Pageable pageable);
}
