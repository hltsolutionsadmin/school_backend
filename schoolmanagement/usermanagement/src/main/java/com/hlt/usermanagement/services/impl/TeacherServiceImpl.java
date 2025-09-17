package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.TeacherDTO;
import com.hlt.usermanagement.model.TeacherModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.repository.SchoolRepository;
import com.hlt.usermanagement.repository.TeacherRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.hlt.usermanagement.services.TeacherService;
import lombok.RequiredArgsConstructor;
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

        if (teacherRepository.findByUser(userModel).isPresent()) {
            throw new HltCustomerException(ErrorCode.DUPLICATE_TEACHER_FOR_USER);
        }

        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setUser(userModel);
        teacherModel.setSchool(schoolModel);
        teacherModel.setPrimaryContact(dto.getPrimaryContact());
        teacherModel.setQualification(dto.getQualification());

        TeacherModel savedTeacher = teacherRepository.save(teacherModel);

        return toDTO(savedTeacher);
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
}