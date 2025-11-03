package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.dto.enums.TicketStatus;
import com.hlt.usermanagement.dto.enums.TicketType;
import com.hlt.usermanagement.model.TicketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Long>,
        JpaSpecificationExecutor<TicketModel> {

    Page<TicketModel> findByCreatedById(Long userId, Pageable pageable);

    Page<TicketModel> findByTypeAndStatus(TicketType type, TicketStatus status, Pageable pageable);

    // Fetch by academic id (no pagination)
    java.util.List<TicketModel> findByAcademic_Id(Long academicId);

    // Fetch by academic id and status
    java.util.List<TicketModel> findByAcademic_IdAndStatus(Long academicId, TicketStatus status);

    // Fetch by academic id and type
    java.util.List<TicketModel> findByAcademic_IdAndType(Long academicId, TicketType type);

    // Fetch by academic id, status and type
    java.util.List<TicketModel> findByAcademic_IdAndStatusAndType(Long academicId, TicketStatus status, TicketType type);

}
