package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.FeeDTO;
import com.hlt.usermanagement.dto.enums.FeeStatus;
import com.hlt.usermanagement.model.FeeModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class FeePopulator implements Populator<FeeModel, FeeDTO> {

    public FeeDTO toDTO(FeeModel source) {
        if (source == null) return null;
        FeeDTO dto = new FeeDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(FeeModel source, FeeDTO target) {
        if (source == null) return;

        target.setId(source.getId());
        target.setAcademicId(source.getAcademic() != null ? source.getAcademic().getId() : null);
        target.setStudentId(source.getStudent() != null ? source.getStudent().getId() : null);
        target.setAmount(source.getAmount());
        target.setDueDate(source.getDueDate());
        target.setPaidDate(source.getPaidDate());
        target.setStatus(source.getStatus() != null ? FeeStatus.valueOf(source.getStatus().name()) : null);
        target.setNotes(source.getNotes());
        target.setInstallmentNo(source.getInstallmentNo());
    }
}
