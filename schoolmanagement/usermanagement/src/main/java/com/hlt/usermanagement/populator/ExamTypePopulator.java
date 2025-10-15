package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.ExamTypeDTO;
import com.hlt.usermanagement.model.ExamTypeModel;
import org.springframework.stereotype.Component;

/**
 * Populator to convert between ExamTypeModel and ExamTypeDTO.
 */
@Component
public class ExamTypePopulator {

    /**
     * Converts ExamTypeModel entity to ExamTypeDTO.
     *
     * @param source The source ExamTypeModel entity.
     * @param target The target ExamTypeDTO.
     */
    public void populateModelToDto(ExamTypeModel source, ExamTypeDTO target) {
        if (source == null || target == null) {
            return;
        }

        target.setId(source.getId());
        target.setTypeName(source.getTypeName());
        target.setDescription(source.getDescription());
    }

    /**
     * Converts ExamTypeDTO to ExamTypeModel.
     * (useful when saving or updating)
     *
     * @param source The source ExamTypeDTO.
     * @param target The target ExamTypeModel.
     */
    public void populateDtoToModel(ExamTypeDTO source, ExamTypeModel target) {
        if (source == null || target == null) {
            return;
        }

        target.setTypeName(source.getTypeName());
        target.setDescription(source.getDescription());
    }

    /**
     * Backwards-compatible alias if older code calls reversePopulate.
     */
    public void reversePopulate(ExamTypeDTO source, ExamTypeModel target) {
        populateDtoToModel(source, target);
    }
}
