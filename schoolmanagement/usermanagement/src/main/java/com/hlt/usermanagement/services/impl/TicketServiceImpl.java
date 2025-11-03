package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.TicketDTO;
import com.hlt.usermanagement.dto.enums.TicketStatus;
import com.hlt.usermanagement.dto.enums.TicketType;
import com.hlt.usermanagement.model.TicketModel;
import com.hlt.usermanagement.populator.TicketPopulator;
import com.hlt.usermanagement.repository.TicketRepository;
import com.hlt.usermanagement.services.TicketService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketPopulator ticketPopulator;

    @Override
    public TicketDTO create(TicketDTO dto) {
        if (dto == null) throw new HltCustomerException(ErrorCode.INVALID_REQUEST);

        TicketModel model = new TicketModel();
        populateModelFromDTO(dto, model);
        model.setStatus(dto.getStatus() != null ? dto.getStatus() : TicketStatus.IN_PROGRESS);

        ticketRepository.save(model);
        return ticketPopulator.toDTO(model);
    }

    @Override
    public TicketDTO update(Long id, TicketDTO dto) {
        TicketModel model = ticketRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TICKET_NOT_FOUND));

        populateModelFromDTO(dto, model);

        ticketRepository.save(model);
        return ticketPopulator.toDTO(model);
    }

    @Override
    public TicketDTO getById(Long id) {
        TicketModel model = ticketRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TICKET_NOT_FOUND));
        return ticketPopulator.toDTO(model);
    }


    @Override
    public Page<TicketDTO> search(Long createdById, String type, String status,
                                  Long academicId, Long b2bUnitId, Pageable pageable) {

        Specification<TicketModel> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (createdById != null)
                predicates.add(cb.equal(root.get("createdBy").get("id"), createdById));

            if (type != null && !type.isBlank()) {
                predicates.add(cb.equal(root.get("type"), TicketType.valueOf(type.toUpperCase())));
            }

            if (status != null && !status.isBlank()) {
                predicates.add(cb.equal(root.get("status"), TicketStatus.valueOf(status.toUpperCase())));
            }

            if (academicId != null)
                predicates.add(cb.equal(root.get("academic").get("id"), academicId));

            if (b2bUnitId != null)
                predicates.add(cb.equal(root.get("b2bUnit").get("id"), b2bUnitId));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return ticketRepository.findAll(spec, pageable)
                .map(ticketPopulator::toDTO);
    }

    @Override
    public void delete(Long id) {
        TicketModel model = ticketRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TICKET_NOT_FOUND));
        ticketRepository.delete(model);
    }

    @Override
    public List<TicketDTO> getTicketsByAcademic(Long academicId, String status, String type) {
        if (academicId == null) {
            throw new HltCustomerException(ErrorCode.INVALID_REQUEST);
        }

        TicketStatus statusEnum = null;
        TicketType typeEnum = null;

        if (status != null && !status.isBlank()) {
            statusEnum = TicketStatus.valueOf(status.toUpperCase());
        }
        if (type != null && !type.isBlank()) {
            typeEnum = TicketType.valueOf(type.toUpperCase());
        }

        List<TicketModel> models;
        if (statusEnum != null && typeEnum != null) {
            models = ticketRepository.findByAcademic_IdAndStatusAndType(academicId, statusEnum, typeEnum);
        } else if (statusEnum != null) {
            models = ticketRepository.findByAcademic_IdAndStatus(academicId, statusEnum);
        } else if (typeEnum != null) {
            models = ticketRepository.findByAcademic_IdAndType(academicId, typeEnum);
        } else {
            models = ticketRepository.findByAcademic_Id(academicId);
        }

        return models.stream()
                .map(ticketPopulator::toDTO)
                .collect(Collectors.toList());
    }

    private void populateModelFromDTO(TicketDTO dto, TicketModel model) {
        if (dto.getTitle() != null) model.setTitle(dto.getTitle());
        if (dto.getContent() != null) model.setContent(dto.getContent());
        if (dto.getType() != null) model.setType(dto.getType());
        if (dto.getStatus() != null) model.setStatus(dto.getStatus());

        if (dto.getCreatedById() != null) {
            var createdBy = new com.hlt.usermanagement.model.UserModel();
            createdBy.setId(dto.getCreatedById());
            model.setCreatedBy(createdBy);
        }

        if (dto.getUserId() != null) {
            var user = new com.hlt.usermanagement.model.UserModel();
            user.setId(dto.getUserId());
            model.setUser(user);
        }

        if (dto.getAcademicId() != null) {
            var academic = new com.hlt.usermanagement.model.AcademicModel();
            academic.setId(dto.getAcademicId());
            model.setAcademic(academic);
        }

        if (dto.getB2bUnitId() != null) {
            var b2bUnit = new com.hlt.usermanagement.model.B2BUnitModel();
            b2bUnit.setId(dto.getB2bUnitId());
            model.setB2bUnit(b2bUnit);
        }
    }
}
