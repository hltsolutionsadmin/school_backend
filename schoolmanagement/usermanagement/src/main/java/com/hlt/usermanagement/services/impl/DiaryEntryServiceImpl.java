package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.DiaryEntryDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.DiaryEntryModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.DiaryEntryPopulator;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.repository.DiaryEntryRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.DiaryEntryService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryEntryServiceImpl implements DiaryEntryService {

    private final DiaryEntryRepository diaryEntryRepository;
    private final AcademicRepository academicRepository;
    private final UserRepository userRepository;
    private final DiaryEntryPopulator diaryEntryPopulator;

    @Override
    public DiaryEntryDTO createDiaryEntry(DiaryEntryDTO dto) {
        AcademicModel academic = academicRepository.findById(dto.getAcademicId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        UserModel user = userRepository.findById(dto.getInitiatedById())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        DiaryEntryModel model = DiaryEntryModel.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .academic(academic)
                .initiatedBy(user)
                .active(dto.getActive() != null ? dto.getActive() : true)
                .attachmentUrl(dto.getAttachmentUrl())
                .priority(dto.getPriority())
                .build();

        DiaryEntryModel saved = diaryEntryRepository.save(model);

        return diaryEntryPopulator.toDTO(saved);
    }

    @Override
    public DiaryEntryDTO updateDiaryEntry(Long id, DiaryEntryDTO dto) {
        DiaryEntryModel model = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.DIARY_ENTRY_NOT_FOUND));

        AcademicModel academic = academicRepository.findById(dto.getAcademicId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        UserModel user = userRepository.findById(dto.getInitiatedById())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setAcademic(academic);
        model.setInitiatedBy(user);
        model.setActive(dto.getActive() != null ? dto.getActive() : model.getActive());
        model.setAttachmentUrl(dto.getAttachmentUrl());
        model.setPriority(dto.getPriority());

        DiaryEntryModel updated = diaryEntryRepository.save(model);

        return diaryEntryPopulator.toDTO(updated);
    }

    @Override
    public Page<DiaryEntryDTO> getDiaryEntriesByAcademic(Long academicId, Pageable pageable) {
        AcademicModel academic = academicRepository.findById(academicId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        Page<DiaryEntryModel> page = diaryEntryRepository.findByAcademic(academic, pageable);

        List<DiaryEntryDTO> dtos = page.getContent().stream()
                .map(diaryEntryPopulator::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public DiaryEntryDTO getDiaryEntryById(Long id) {
        DiaryEntryModel model = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.DIARY_ENTRY_NOT_FOUND));

        return diaryEntryPopulator.toDTO(model);
    }

    @Override
    public void deleteDiaryEntry(Long id) {
        DiaryEntryModel model = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.DIARY_ENTRY_NOT_FOUND));

        diaryEntryRepository.delete(model);
    }
}
