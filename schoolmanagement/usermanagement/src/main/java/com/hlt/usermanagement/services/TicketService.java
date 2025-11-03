package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TicketService {

    TicketDTO create(TicketDTO dto);

    TicketDTO update(Long id, TicketDTO dto);

    TicketDTO getById(Long id);

    Page<TicketDTO> search(Long createdById,
                           String type,
                           String status,
                           Long academicId,
                           Long b2bUnitId,
                           Pageable pageable);
    void delete(Long id);

    List<TicketDTO> getTicketsByAcademic(Long academicId, String status, String type);
}
