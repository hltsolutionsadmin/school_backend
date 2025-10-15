package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.AcademicUnitDTO;
import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.TeacherModel;
import com.hlt.usermanagement.repository.*;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.hlt.usermanagement.model.AcademicUnitModel;
import com.hlt.usermanagement.populator.AcademicUnitPopulator;
import com.hlt.usermanagement.populator.StudentPopulator;
import com.hlt.usermanagement.populator.SubjectPopulator;
import com.hlt.usermanagement.services.AcademicUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AcademicUnitServiceImpl implements AcademicUnitService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final SchoolRepository SchoolRepository;
    private final TeacherRepository TeacherRepository;



    private final AcademicUnitPopulator classPopulator;
    private final StudentPopulator studentPopulator;
    private final SubjectPopulator subjectPopulator;

    @Override
    public AcademicUnitDTO createClass(AcademicUnitDTO dto) {
        AcademicUnitModel model = new AcademicUnitModel();
        model.setClassName(dto.getClassName());
        model.setSection(dto.getSection());
        model.setAcademicYear(dto.getAcademicYear());
        model.setCapacity(dto.getCapacity());
        model.setRoomNumber(dto.getRoomNumber());
        model.setIsActive(dto.getIsActive());
        model.setTimetable(dto.getTimetable());
        model.setNotes(dto.getNotes());

        SchoolModel schoolModel = SchoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));

        model.setSchool(schoolModel);

        AcademicUnitModel savedModel = classRepository.save(model);
        return classPopulator.toDTO(savedModel);
    }

    @Override
    public AcademicUnitDTO updateClass(Long id, AcademicUnitDTO dto) {
        AcademicUnitModel model = classRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.CLASS_NOT_FOUND));

        // Basic property updates
        model.setClassName(dto.getClassName());
        model.setSection(dto.getSection());
        model.setAcademicYear(dto.getAcademicYear());
        model.setCapacity(dto.getCapacity());
        model.setRoomNumber(dto.getRoomNumber());
        model.setIsActive(dto.getIsActive());
        model.setTimetable(dto.getTimetable());
        model.setNotes(dto.getNotes());

        // CRITICAL FIX 1: Update the School relationship if the schoolId is provided in the DTO.
        if (dto.getSchoolId() != null) {
            SchoolModel schoolModel = SchoolRepository.findById(dto.getSchoolId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));
            model.setSchool(schoolModel);
        }

        if (dto.getClassTeacherId() != null) {
            TeacherModel teacherModel = TeacherRepository.findById(dto.getClassTeacherId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.TEACHER_NOT_FOUND));
            model.setClassTeacher(teacherModel);
        } else {
            model.setClassTeacher(null);
        }

        AcademicUnitModel updatedModel = classRepository.save(model);
        return classPopulator.toDTO(updatedModel);
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicUnitDTO getClassById(Long id) {
        return classRepository.findById(id)
                .map(classPopulator::toDTO)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.CLASS_NOT_FOUND));
    }

    @Override
    public void deleteClass(Long id) {
        if (!classRepository.existsById(id)) {
            throw new HltCustomerException(ErrorCode.CLASS_NOT_FOUND);
        }
        classRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsInClass(Long classId, Pageable pageable) {
        return studentRepository.findByClassModelId(classId, pageable)
                .getContent()
                .stream()
                .map(studentPopulator::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> getSubjectsInClass(Long classId, Pageable pageable) {
        return subjectRepository.findByClassModelId(classId, pageable)
                .getContent()
                .stream()
                .map(subjectPopulator::toDTO)
                .collect(Collectors.toList());
    }
}


