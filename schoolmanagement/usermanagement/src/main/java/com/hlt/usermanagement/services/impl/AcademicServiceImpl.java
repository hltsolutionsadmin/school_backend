package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.AcademicDTO;
import com.hlt.usermanagement.dto.AcademicUserDTO;
import com.hlt.usermanagement.dto.AssignUserDTO;

import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.AcademicUserMapping;
import com.hlt.usermanagement.model.B2BUnitModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.AcademicPopulator;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.repository.AcademicUserMappingRepository;
import com.hlt.usermanagement.repository.B2BUnitRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.AcademicService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademicServiceImpl implements AcademicService {

    private final AcademicRepository academicRepository;
    private final AcademicUserMappingRepository academicUserMappingRepository;
    private final UserRepository userRepository;
    private final AcademicPopulator academicPopulator;
    private final B2BUnitRepository b2bUnitRepository;

    @Override
    public AcademicDTO createAcademic(AcademicDTO dto) {
        AcademicModel model = new AcademicModel();
        B2BUnitModel b2bUnit = b2bUnitRepository.findById(dto.getB2bUnitId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));
        model.setName(dto.getName());
        model.setCode(dto.getCode());
        model.setDescription(dto.getDescription());
        model.setActive(dto.getActive() != null ? dto.getActive() : true);
        model.setSortOrder(dto.getSortOrder());
        model.setSection(dto.getSection());
        model.setCapacity(dto.getCapacity());
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setSubjectCount(dto.getSubjectCount());
        model.setTeacherCount(dto.getTeacherCount());
        model.setNotes(dto.getNotes());

        model.setB2bUnit(b2bUnit);
        AcademicModel saved = academicRepository.save(model);
        return academicPopulator.toDTO(saved);
    }


    @Override
    public Page<AcademicDTO> getAllAcademics(Long b2bUnitId, Pageable pageable) {
        Page<AcademicModel> page = academicRepository.findByB2bUnitId(b2bUnitId, pageable);
        List<AcademicDTO> dtos = page.getContent()
                .stream()
                .map(academicPopulator::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public void assignUsers(Long academicId, AssignUserDTO dto) {
        AcademicModel academic = academicRepository.findById(academicId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        List<UserModel> users = userRepository.findAllById(dto.getUserIds());
        if (users.size() != dto.getUserIds().size()) {
            throw new HltCustomerException(ErrorCode.USER_NOT_FOUND);
        }

        List<AcademicUserMapping> mappings = users.stream()
                .map(user -> AcademicUserMapping.builder()
                        .academic(academic)
                        .user(user)
                        .role(dto.getRole())
                        .build())
                .collect(Collectors.toList());

        academicUserMappingRepository.saveAll(mappings);
    }


    @Override
    public Page<AcademicUserDTO> getUsersInAcademic(Long academicId, Pageable pageable) {
        AcademicModel academic = academicRepository.findById(academicId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        Page<AcademicUserMapping> mappingsPage = academicUserMappingRepository.findByAcademic(academic, pageable);

        if (mappingsPage.isEmpty()) {
            throw new HltCustomerException(ErrorCode.USER_NOT_FOUND);
        }

        List<AcademicUserDTO> dtos = mappingsPage.getContent().stream()
                .map(mapping -> {
                    UserModel user = mapping.getUser();
                    return AcademicUserDTO.builder()
                            .userId(user.getId())
                            .fullName(user.getFullName())
                            .email(user.getEmail())
                            .role(mapping.getRole())
                            .build();
                })
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, mappingsPage.getTotalElements());
    }


    @Override
    public Page<AcademicDTO> getAcademicsByUserId(Long userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<AcademicModel> academicsPage = academicUserMappingRepository.findAllAcademicsByUserId(userId, pageable);

        if (academicsPage.isEmpty()) {
            throw new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND, "No academics found for user");
        }

        return academicsPage.map(academicPopulator::toDTO);
    }


}
