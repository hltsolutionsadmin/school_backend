package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.HomeworkDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.HomeworkModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.HomeworkPopulator;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.repository.HomeworkRepository;
import com.hlt.usermanagement.repository.UserRepository;

import com.hlt.usermanagement.services.HomeworkService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final AcademicRepository academicRepository;
    private final UserRepository userRepository;
    private final HomeworkPopulator homeworkPopulator;

    @Override
    public HomeworkDTO createHomework(HomeworkDTO homeworkDTO) {
        AcademicModel academic = academicRepository.findById(homeworkDTO.getAcademicId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ACADEMIC_NOT_FOUND));
        UserModel initiatedBy = userRepository.findById(homeworkDTO.getInitiatedByUserId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        HomeworkModel homework = homeworkPopulator.toModel(homeworkDTO, academic, initiatedBy);
        homeworkRepository.save(homework);

        return homeworkPopulator.toDTO(homework);
    }

    @Override
    public HomeworkDTO updateHomework(Long id, HomeworkDTO homeworkDTO) {
        HomeworkModel existing = homeworkRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.HOMEWORK_NOT_FOUND));

        AcademicModel academic = academicRepository.findById(homeworkDTO.getAcademicId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ACADEMIC_NOT_FOUND));
        UserModel initiatedBy = userRepository.findById(homeworkDTO.getInitiatedByUserId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        HomeworkModel updated = homeworkPopulator.toModel(homeworkDTO, academic, initiatedBy);
        updated.setId(existing.getId());
        homeworkRepository.save(updated);

        return homeworkPopulator.toDTO(updated);
    }

    @Override
    public HomeworkDTO getHomeworkById(Long id) {
        HomeworkModel homework = homeworkRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.HOMEWORK_NOT_FOUND));
        return homeworkPopulator.toDTO(homework);
    }

    @Override
    public Page<HomeworkDTO> getAllHomework(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<HomeworkModel> homeworkPage = homeworkRepository.findAll(pageRequest);

        List<HomeworkDTO> dtos = homeworkPage.getContent()
                .stream()
                .map(homeworkPopulator::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageRequest, homeworkPage.getTotalElements());
    }

    @Override
    public void deleteHomework(Long id) {
        HomeworkModel homework = homeworkRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.HOMEWORK_NOT_FOUND));
        homeworkRepository.delete(homework);
    }
}
