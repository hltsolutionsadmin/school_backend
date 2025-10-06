package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.DiaryEntryDTO;
import com.hlt.usermanagement.model.DiaryEntryModel;
import com.hlt.usermanagement.model.StaffModel;
import com.hlt.usermanagement.model.StudentModel;
import com.hlt.usermanagement.populator.DiaryEntryPopulator;
import com.hlt.usermanagement.repository.DiaryEntryRepository;
import com.hlt.usermanagement.repository.StaffRepository;
import com.hlt.usermanagement.repository.StudentRepository;
import com.hlt.usermanagement.services.DiaryEntryService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryEntryServiceImpl implements DiaryEntryService {

    private final DiaryEntryRepository diaryEntryRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final DiaryEntryPopulator diaryEntryPopulator;

    @Override
    public DiaryEntryDTO createDiaryEntry(DiaryEntryDTO dto) {
        StudentModel student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));
        StaffModel staff = staffRepository.findById(dto.getStaffId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        DiaryEntryModel entry = new DiaryEntryModel();
        entry.setStudent(student);
        entry.setStaff(staff);
        entry.setSubject(dto.getSubject());
        entry.setNote(dto.getSubmissionNote());
        entry.setEntryDate(dto.getEntryDate());

        entry = diaryEntryRepository.save(entry);

        DiaryEntryDTO response = new DiaryEntryDTO();
        diaryEntryPopulator.populate(entry, response);
        return response;
    }

    @Override
    public DiaryEntryDTO getDiaryEntry(Long id) {
        DiaryEntryModel entry = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        DiaryEntryDTO dto = new DiaryEntryDTO();
        diaryEntryPopulator.populate(entry, dto);
        return dto;
    }

    @Override
    public List<DiaryEntryDTO> getAllDiaryEntries() {
        return diaryEntryRepository.findAll().stream().map(entry -> {
            DiaryEntryDTO dto = new DiaryEntryDTO();
            diaryEntryPopulator.populate(entry, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public DiaryEntryDTO updateDiaryEntry(Long id, DiaryEntryDTO dto) {
        DiaryEntryModel entry = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        entry.setSubject(dto.getSubject());
        entry.setNote(dto.getSubmissionNote());
        entry.setEntryDate(dto.getEntryDate());

        entry = diaryEntryRepository.save(entry);

        DiaryEntryDTO response = new DiaryEntryDTO();
        diaryEntryPopulator.populate(entry, response);
        return response;
    }

    @Override
    public void deleteDiaryEntry(Long id) {
        DiaryEntryModel entry = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));
        diaryEntryRepository.delete(entry);
    }
}
