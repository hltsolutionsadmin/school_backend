package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.TicketDTO;
import com.hlt.usermanagement.model.*;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class TicketPopulator implements Populator<TicketModel, TicketDTO> {

    public TicketDTO toDTO(TicketModel source) {
        if (source == null) return null;
        TicketDTO dto = new TicketDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(TicketModel source, TicketDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setCreatedById(source.getCreatedBy() != null ? source.getCreatedBy().getId() : null);
        target.setUserId(source.getUser() != null ? source.getUser().getId() : null);
        target.setAcademicId(source.getAcademic() != null ? source.getAcademic().getId() : null);
        target.setB2bUnitId(source.getB2bUnit() != null ? source.getB2bUnit().getId() : null);
        target.setType(source.getType());
        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        target.setStatus(source.getStatus());
    }
}
