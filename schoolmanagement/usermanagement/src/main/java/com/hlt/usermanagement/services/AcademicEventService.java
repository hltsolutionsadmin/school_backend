package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.AcademicEventDTO;
import org.springframework.data.domain.Page;

public interface AcademicEventService {

    AcademicEventDTO createEvent(AcademicEventDTO dto);

    AcademicEventDTO updateEvent(Long id, AcademicEventDTO dto);

    AcademicEventDTO getEventById(Long id);

    Page<AcademicEventDTO> getAllEvents(int page, int size);

    void deleteEvent(Long id);
}
