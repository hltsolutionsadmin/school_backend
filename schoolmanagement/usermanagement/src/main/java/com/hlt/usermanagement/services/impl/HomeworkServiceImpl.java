package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.HomeworkDTO;
import com.hlt.usermanagement.model.HomeworkModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.StaffModel;
import com.hlt.usermanagement.model.StudentModel;
import com.hlt.usermanagement.populator.HomeworkPopulator;
import com.hlt.usermanagement.repository.HomeworkRepository;
import com.hlt.usermanagement.repository.SchoolRepository;
import com.hlt.usermanagement.repository.StaffRepository;
import com.hlt.usermanagement.repository.StudentRepository;
import com.hlt.usermanagement.services.HomeworkService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private HomeworkPopulator homeworkPopulator;

    @Override
    public HomeworkDTO createHomework(HomeworkDTO homeworkDTO) {
        StudentModel student = studentRepository.findById(homeworkDTO.getStudentId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
        StaffModel staff = staffRepository.findById(homeworkDTO.getStaffId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
        SchoolModel school = schoolRepository.findById(homeworkDTO.getSchoolId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));

        HomeworkModel homework = new HomeworkModel();
        homework.setStudent(student);
        homework.setStaff(staff);
        homework.setSchool(school);
        homework.setSubject(homeworkDTO.getSubject());
        homework.setDescription(homeworkDTO.getDescription());
        homework.setAssignDate(homeworkDTO.getAssignDate());
        homework.setDueDate(homeworkDTO.getDueDate());
        homework.setStatus(homeworkDTO.getStatus() != null ? homeworkDTO.getStatus() : com.hlt.usermanagement.dto.enums.HomeworkStatus.ASSIGNED);
        homework.setRemarks(homeworkDTO.getRemarks());

        HomeworkModel saved = homeworkRepository.save(homework);

        HomeworkDTO responseDTO = new HomeworkDTO();
        homeworkPopulator.populate(saved, responseDTO);
        return responseDTO;
    }

    @Override
    public HomeworkDTO updateHomework(Long homeworkId, HomeworkDTO homeworkDTO) {
        HomeworkModel homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.HOMEWORK_NOT_FOUND));

        if (homeworkDTO.getSubject() != null) homework.setSubject(homeworkDTO.getSubject());
        if (homeworkDTO.getDescription() != null) homework.setDescription(homeworkDTO.getDescription());
        if (homeworkDTO.getAssignDate() != null) homework.setAssignDate(homeworkDTO.getAssignDate());
        if (homeworkDTO.getDueDate() != null) homework.setDueDate(homeworkDTO.getDueDate());
        if (homeworkDTO.getStatus() != null) homework.setStatus(homeworkDTO.getStatus());
        if (homeworkDTO.getRemarks() != null) homework.setRemarks(homeworkDTO.getRemarks());

        HomeworkModel updated = homeworkRepository.save(homework);
        HomeworkDTO responseDTO = new HomeworkDTO();
        homeworkPopulator.populate(updated, responseDTO);
        return responseDTO;
    }

    @Override
    public HomeworkDTO getHomeworkById(Long homeworkId) {
        HomeworkModel homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.HOMEWORK_NOT_FOUND));
        HomeworkDTO dto = new HomeworkDTO();
        homeworkPopulator.populate(homework, dto);
        return dto;
    }

    @Override
    public Page<HomeworkDTO> getHomeworkByStudent(Long studentId, Pageable pageable) {
        StudentModel student = studentRepository.findById(studentId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
        return homeworkRepository.findByStudent(student, pageable)
                .map(h -> {
                    HomeworkDTO dto = new HomeworkDTO();
                    homeworkPopulator.populate(h, dto);
                    return dto;
                });
    }

    @Override
    public List<HomeworkDTO> getHomeworkByStaff(Long staffId) {
        StaffModel staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
        return homeworkRepository.findByStaff(staff)
                .stream()
                .map(h -> {
                    HomeworkDTO dto = new HomeworkDTO();
                    homeworkPopulator.populate(h, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<HomeworkDTO> getHomeworkByStaff(Long staffId, Pageable pageable) {
        StaffModel staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
        return homeworkRepository.findByStaff(staff, pageable)
                .map(h -> {
                    HomeworkDTO dto = new HomeworkDTO();
                    homeworkPopulator.populate(h, dto);
                    return dto;
                });
    }

    @Override
    public void deleteHomework(Long homeworkId) {
        HomeworkModel homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.HOMEWORK_NOT_FOUND));
        homeworkRepository.delete(homework);
    }
}
