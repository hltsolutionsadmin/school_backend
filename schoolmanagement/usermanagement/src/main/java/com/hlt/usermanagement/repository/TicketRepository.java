package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.TicketType;
import com.hlt.usermanagement.model.TicketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Long> {

    Page<TicketModel> findByCreatedById(Long userId, Pageable pageable);

    Page<TicketModel> findByTypeAndStatus(TicketType type,
                                          CommunicationStatus status,
                                          Pageable pageable);
}
