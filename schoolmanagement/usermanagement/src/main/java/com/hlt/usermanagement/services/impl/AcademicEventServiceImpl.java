package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.AcademicEventDTO;
import com.hlt.usermanagement.model.AcademicEventModel;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.populator.AcademicEventPopulator;
import com.hlt.usermanagement.repository.AcademicEventRepository;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.services.AcademicEventService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AcademicEventServiceImpl implements AcademicEventService {

    private final AcademicEventRepository academicEventRepository;
    private final AcademicRepository academicUnitRepository;
    private final AcademicEventPopulator academicEventPopulator;

    @Override
    public AcademicEventDTO createEvent(AcademicEventDTO dto) {
        AcademicModel academicUnit = academicUnitRepository.findById(dto.getAcademicUnitId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ACADEMIC_NOT_FOUND));

        AcademicEventModel model = new AcademicEventModel();
        model.setAcademicUnit(academicUnit);
        model.setType(dto.getType());
        model.setName(dto.getName());
        model.setDate(dto.getDate());
        model.setDetails(dto.getDetails());
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());

        AcademicEventModel saved = academicEventRepository.save(model);
        return academicEventPopulator.toDTO(saved);
    }

    @Override
    public AcademicEventDTO updateEvent(Long id, AcademicEventDTO dto) {
        AcademicEventModel existing = academicEventRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EVENT_NOT_FOUND));

        AcademicModel academicUnit = academicUnitRepository.findById(dto.getAcademicUnitId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ACADEMIC_NOT_FOUND));

        existing.setAcademicUnit(academicUnit);
        existing.setType(dto.getType());
        existing.setName(dto.getName());
        existing.setDate(dto.getDate());
        existing.setDetails(dto.getDetails());
        existing.setUpdatedAt(LocalDateTime.now());

        AcademicEventModel updated = academicEventRepository.save(existing);
        return academicEventPopulator.toDTO(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicEventDTO getEventById(Long id) {
        AcademicEventModel event = academicEventRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EVENT_NOT_FOUND));
        return academicEventPopulator.toDTO(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AcademicEventDTO> getAllEvents(int page, int size) {
        return academicEventRepository.findAll(PageRequest.of(page, size))
                .map(academicEventPopulator::toDTO);
    }

    @Override
    public void deleteEvent(Long id) {
        AcademicEventModel existing = academicEventRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EVENT_NOT_FOUND));
        academicEventRepository.delete(existing);
    }
}
