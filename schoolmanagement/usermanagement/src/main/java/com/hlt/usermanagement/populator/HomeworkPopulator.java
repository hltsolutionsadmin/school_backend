package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.HomeworkDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.HomeworkModel;
import com.hlt.usermanagement.model.UserModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class HomeworkPopulator implements Populator<HomeworkModel, HomeworkDTO> {

    @Override
    public void populate(HomeworkModel source, HomeworkDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setAcademicId(source.getAcademic() != null ? source.getAcademic().getId() : null);
        target.setInitiatedByUserId(source.getInitiatedBy() != null ? source.getInitiatedBy().getId() : null);
        target.setInitiatedByUserName(source.getInitiatedBy() != null ? source.getInitiatedBy().getFullName() : null);
        target.setDueDate(source.getDueDate());
        target.setActive(source.getActive());
        target.setAttachmentUrl(source.getAttachmentUrl());
        target.setPriority(source.getPriority());
    }

    public HomeworkDTO toDTO(HomeworkModel source) {
        if (source == null) return null;
        HomeworkDTO dto = new HomeworkDTO();
        populate(source, dto);
        return dto;
    }

    public HomeworkModel toModel(HomeworkDTO dto, AcademicModel academic, UserModel initiatedBy) {
        if (dto == null) return null;

        return HomeworkModel.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .academic(academic)
                .initiatedBy(initiatedBy)
                .dueDate(dto.getDueDate())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .attachmentUrl(dto.getAttachmentUrl())
                .priority(dto.getPriority())
                .build();
    }
}
