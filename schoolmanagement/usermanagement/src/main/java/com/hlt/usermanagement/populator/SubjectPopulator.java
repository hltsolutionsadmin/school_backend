package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.model.SubjectModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SubjectPopulator implements Populator<SubjectModel, SubjectDTO> {

    @Override
    public void populate(SubjectModel source, SubjectDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCode(source.getCode());

        if (source.getClassModel() != null) {
            target.setClassId(source.getClassModel().getId());
            target.setClassName(source.getClassModel().getClassName());
            target.setSection(source.getClassModel().getSection());
        }

        if (source.getTeachers() != null && !source.getTeachers().isEmpty()) {
            Set<Long> teacherIds = source.getTeachers().stream()
                    .map(t -> t.getId())
                    .filter(id -> id != null)
                    .collect(Collectors.toSet());

            Set<String> teacherNames = source.getTeachers().stream()
                    .map(t -> (t.getUser() != null ? t.getUser().getFullName() : null))
                    .filter(name -> name != null && !name.isBlank())
                    .collect(Collectors.toSet());

            target.setTeacherIds(teacherIds);
            target.setTeacherNames(teacherNames);
        } else {
            target.setTeacherIds(Collections.emptySet());
            target.setTeacherNames(Collections.emptySet());
        }
    }

    public SubjectDTO toDTO(SubjectModel source) {
        if (source == null) return null;
        SubjectDTO dto = new SubjectDTO();
        populate(source, dto);
        return dto;
    }
}