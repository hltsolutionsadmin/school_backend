package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.UserCommunicationDTO;
import com.hlt.usermanagement.model.UserCommunicationModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;


@Component
public class UserCommunicationPopulator implements Populator<UserCommunicationModel, UserCommunicationDTO> {

    public UserCommunicationDTO toDTO(UserCommunicationModel source) {
        if (source == null) return null;
        UserCommunicationDTO dto = new UserCommunicationDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(UserCommunicationModel source, UserCommunicationDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setCreatedById(source.getCreatedBy() != null ? source.getCreatedBy().getId() : null);
        target.setType(source.getType());
        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        target.setStatus(source.getStatus());
        target.setCreatedAt(source.getCreatedAt());
        target.setUpdatedAt(source.getUpdatedAt());
    }
}
