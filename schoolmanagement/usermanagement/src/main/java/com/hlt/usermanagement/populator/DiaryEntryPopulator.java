package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.DiaryEntryDTO;
import com.hlt.usermanagement.model.DiaryEntryModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class DiaryEntryPopulator implements Populator<DiaryEntryModel, DiaryEntryDTO> {


    public DiaryEntryDTO toDTO(DiaryEntryModel source) {
        if (source == null) return null;
        DiaryEntryDTO dto = new DiaryEntryDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(DiaryEntryModel source, DiaryEntryDTO target) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setAcademicId(source.getAcademic() != null ? source.getAcademic().getId() : null);
        target.setInitiatedById(source.getInitiatedBy() != null ? source.getInitiatedBy().getId() : null);
        target.setActive(source.getActive());
        target.setAttachmentUrl(source.getAttachmentUrl());
        target.setPriority(source.getPriority());
    }

    public void populateModel(DiaryEntryDTO source, DiaryEntryModel target) {
        if (source == null || target == null) return;
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setActive(source.getActive() != null ? source.getActive() : true);
        target.setAttachmentUrl(source.getAttachmentUrl());
        target.setPriority(source.getPriority());
    }
}
