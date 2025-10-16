package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.GrievanceDTO;
import com.hlt.usermanagement.dto.enums.GrievanceStatus;
import com.hlt.usermanagement.dto.enums.GrievanceType;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.hlt.usermanagement.model.GrievanceModel;
import com.hlt.usermanagement.populator.GrievancePopulator;
import com.hlt.usermanagement.repository.GrievanceRepository;
import com.hlt.usermanagement.services.GrievanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class GrievanceServiceImpl implements GrievanceService {

    private final GrievanceRepository repository;
    private final GrievancePopulator populator;

    public GrievanceServiceImpl(GrievanceRepository repository, GrievancePopulator populator) {
        this.repository = repository;
        this.populator = populator;
    }

    @Override
    public GrievanceDTO createGrievance(GrievanceDTO dto) {
        log.info("Creating grievance with title: {}", dto.getTitle());

        GrievanceModel entity = new GrievanceModel();
        populator.populateEntity(dto, entity);

        // Default status if not set
        if (entity.getStatus() == null) {
            entity.setStatus(GrievanceStatus.RAISED);
        }

        // Prevent duplicate grievance (example: based on title or reference)
        boolean exists = repository.existsByTitleAndActiveTrue(entity.getTitle());
        if (exists) {
            throw new HltCustomerException(ErrorCode.SUBJECT_ALREADY_EXISTS,
                    "Grievance already exists with title: " + entity.getTitle());
        }

        GrievanceModel saved = repository.save(entity);
        log.info("Grievance saved successfully with ID: {}", saved.getId());

        GrievanceDTO response = new GrievanceDTO();
        populator.populateDTO(saved, response);
        return response;
    }

    @Override
    public GrievanceDTO updateGrievance(Long id, GrievanceDTO dto) {
        log.info("Updating grievance with ID: {}", id);

        GrievanceModel existing = repository.findById(id)
                .filter(GrievanceModel::isActive)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.DATA_NOT_FOUND,
                        "Grievance not found with ID: " + id));

        populator.populateEntity(dto, existing);
        GrievanceModel updated = repository.save(existing);

        log.info("Grievance updated successfully with ID: {}", updated.getId());

        GrievanceDTO response = new GrievanceDTO();
        populator.populateDTO(updated, response);
        return response;
    }

    @Override
    public void deleteGrievance(Long id) {
        log.info("Deleting grievance with ID: {}", id);

        GrievanceModel existing = repository.findById(id)
                .filter(GrievanceModel::isActive)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.DATA_NOT_FOUND,
                        "Grievance not found with ID: " + id));

        existing.setActive(false);
        repository.save(existing);
        log.info("Grievance soft-deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public GrievanceDTO getById(Long id) {
        log.info("Fetching grievance with ID: {}", id);

        GrievanceModel model = repository.findById(id)
                .filter(GrievanceModel::isActive)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.DATA_NOT_FOUND,
                        "Grievance not found with ID: " + id));

        GrievanceDTO dto = new GrievanceDTO();
        populator.populateDTO(model, dto);
        return dto;
    }

    @Override
    public Page<GrievanceDTO> getAll(Pageable pageable) {
        log.info("Fetching all grievances (page: {}, size: {})", pageable.getPageNumber(), pageable.getPageSize());

        return repository.findByActiveTrue(pageable)
                .map(entity -> {
                    GrievanceDTO dto = new GrievanceDTO();
                    populator.populateDTO(entity, dto);
                    return dto;
                });
    }

    @Override
    public Page<GrievanceDTO> getByType(GrievanceType type, Pageable pageable) {
        log.info("Fetching grievances by type: {}", type);

        return repository.findByTypeAndActiveTrue(type, pageable)
                .map(entity -> {
                    GrievanceDTO dto = new GrievanceDTO();
                    populator.populateDTO(entity, dto);
                    return dto;
                });
    }

    @Override
    public Page<GrievanceDTO> getByStatus(GrievanceStatus status, Pageable pageable) {
        log.info("Fetching grievances by status: {}", status);

        return repository.findByStatusAndActiveTrue(status, pageable)
                .map(entity -> {
                    GrievanceDTO dto = new GrievanceDTO();
                    populator.populateDTO(entity, dto);
                    return dto;
                });
    }
}
