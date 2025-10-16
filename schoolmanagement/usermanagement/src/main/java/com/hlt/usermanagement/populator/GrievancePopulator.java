package com.hlt.usermanagement.populator;


import com.hlt.usermanagement.dto.GrievanceDTO;
import com.hlt.usermanagement.model.GrievanceModel;
import org.springframework.stereotype.Component;

@Component
public class GrievancePopulator {

    /**
     * Populates an entity from DTO (used while creating/updating grievances)
     */
    public void populateEntity(GrievanceDTO source, GrievanceModel target) {
        if (source == null || target == null) {
            return;
        }

        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        target.setAttachmentUrl(source.getAttachmentUrl());
        target.setActive(source.isActive());
    }

    /**
     * Populates a DTO from Entity (used for responses)
     */
    public void populateDTO(GrievanceModel source, GrievanceDTO target) {
        if (source == null || target == null) {
            return;
        }

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        target.setAttachmentUrl(source.getAttachmentUrl());
        target.setActive(source.isActive());
        target.setCreatedAt(source.getCreatedAt());
        target.setUpdatedAt(source.getUpdatedAt());
    }
}


