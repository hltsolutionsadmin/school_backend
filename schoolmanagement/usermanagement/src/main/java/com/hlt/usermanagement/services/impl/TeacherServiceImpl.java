package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.TeacherDTO;
import com.hlt.usermanagement.dto.ClassDTO;
import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.model.*;
import com.hlt.usermanagement.repository.SchoolRepository;
import com.hlt.usermanagement.repository.TeacherRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.hlt.usermanagement.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public TeacherDTO createTeacher(TeacherDTO dto) {
        UserModel userModel = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        SchoolModel schoolModel = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));

        teacherRepository.findByUser(userModel).ifPresent(existingTeacher -> {
            throw new HltCustomerException(ErrorCode.DUPLICATE_TEACHER_FOR_USER);
        });

        TeacherModel teacherModel = toEntity(dto, userModel, schoolModel);
        TeacherModel savedTeacher = teacherRepository.save(teacherModel);

        return toDTO(savedTeacher);
    }

    @Override
    public TeacherDTO updateTeacher(Long teacherId, TeacherDTO dto) {
        TeacherModel teacherModel = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TEACHER_NOT_FOUND));

        if (dto.getPrimaryContact() != null) {
            teacherModel.setPrimaryContact(dto.getPrimaryContact());
        }
        if (dto.getQualification() != null) {
            teacherModel.setQualification(dto.getQualification());
        }

        TeacherModel updated = teacherRepository.save(teacherModel);
        return toDTO(updated);
    }

    @Override
    public TeacherDTO getTeacherById(Long teacherId) {
        TeacherModel teacherModel = teacherRepository.findByIdWithClassInCharge(teacherId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TEACHER_NOT_FOUND));
        return toDTO(teacherModel);
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        TeacherModel teacherModel = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TEACHER_NOT_FOUND));
        teacherRepository.delete(teacherModel);
    }

    @Override
    public Page<ClassDTO> getClassesAssignedToTeacher(Long teacherId, Pageable pageable) {
        TeacherModel teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TEACHER_NOT_FOUND));

        return teacher.getClasses().stream()
                .map(this::toClassDTO)
                .collect(java.util.stream.Collectors.collectingAndThen(
                        java.util.stream.Collectors.toList(),
                        list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())
                ));
    }

    @Override
    public Page<SubjectDTO> getSubjectsTaughtByTeacher(Long teacherId, Pageable pageable) {
        TeacherModel teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TEACHER_NOT_FOUND));

        return teacher.getSubjects().stream()
                .map(this::toSubjectDTO)
                .collect(java.util.stream.Collectors.collectingAndThen(
                        java.util.stream.Collectors.toList(),
                        list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())
                ));
    }

    private TeacherModel toEntity(TeacherDTO dto, UserModel user, SchoolModel school) {
        TeacherModel model = new TeacherModel();
        model.setUser(user);
        model.setSchool(school);
        model.setPrimaryContact(dto.getPrimaryContact());
        model.setQualification(dto.getQualification());
        return model;
    }

    private TeacherDTO toDTO(TeacherModel model) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setSchoolId(model.getSchool().getId());
        dto.setPrimaryContact(model.getPrimaryContact());
        dto.setQualification(model.getQualification());
        return dto;
    }

    private ClassDTO toClassDTO(ClassModel classModel) {
        ClassDTO dto = new ClassDTO();
        dto.setId(classModel.getId());
        dto.setClassName(classModel.getClassName());
        dto.setSection(classModel.getSection());
        return dto;
    }

    private SubjectDTO toSubjectDTO(SubjectModel subjectModel) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subjectModel.getId());
        dto.setName(subjectModel.getName());
        dto.setCode(subjectModel.getCode());
        return dto;
    }
}
