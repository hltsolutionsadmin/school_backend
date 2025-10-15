package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.AcademicUnitDTO;
import com.hlt.usermanagement.model.AcademicUnitModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AcademicUnitPopulator implements Populator<AcademicUnitModel, AcademicUnitDTO> {

    @Override
    public void populate(AcademicUnitModel source, AcademicUnitDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setClassName(source.getClassName());
        target.setSection(source.getSection());
        target.setAcademicYear(source.getAcademicYear());

        if (source.getSchool() != null) {
            target.setSchoolId(source.getSchool().getId());
            target.setSchoolName(source.getSchool().getName());
        }

        if (source.getClassTeacher() != null) {
            target.setClassTeacherId(source.getClassTeacher().getId());
            if (source.getClassTeacher().getUser() != null) {
                target.setClassTeacherName(source.getClassTeacher().getUser().getFullName());
            }
        }

        target.setCapacity(source.getCapacity());
        target.setRoomNumber(source.getRoomNumber());
        target.setIsActive(source.getIsActive());
        target.setTimetable(source.getTimetable());
        target.setNotes(source.getNotes());

        if (source.getStudents() != null) {
            target.setStudentIds(source.getStudents()
                    .stream()
                    .map(s -> s.getId())
                    .collect(Collectors.toList()));
        }

        if (source.getSubjects() != null) {
            target.setSubjectIds(source.getSubjects()
                    .stream()
                    .map(s -> s.getId())
                    .collect(Collectors.toSet()));
        }
    }

    public AcademicUnitDTO toDTO(AcademicUnitModel source) {
        if (source == null) return null;
        AcademicUnitDTO dto = new AcademicUnitDTO();
        populate(source, dto);
        return dto;
    }
}