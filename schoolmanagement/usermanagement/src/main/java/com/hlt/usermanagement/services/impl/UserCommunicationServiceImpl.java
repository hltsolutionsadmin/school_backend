package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.UserCommunicationDTO;
import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import com.hlt.usermanagement.model.UserCommunicationModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.UserCommunicationPopulator;
import com.hlt.usermanagement.repository.UserCommunicationRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.UserCommunicationService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserCommunicationServiceImpl implements UserCommunicationService {

    private final UserCommunicationRepository communicationRepository;
    private final UserRepository userRepository;
    private final UserCommunicationPopulator populator;

    @Override
    public UserCommunicationDTO createCommunication(UserCommunicationDTO dto) {
        UserModel user = userRepository.findById(dto.getCreatedById())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        UserCommunicationModel model = UserCommunicationModel.builder()
                .type(dto.getType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .updatedAt(dto.getUpdatedAt())
                .createdBy(user)
                .build();

        UserCommunicationModel saved = communicationRepository.save(model);

        return populator.toDTO(saved);
    }


    @Override
    public UserCommunicationDTO getCommunicationById(Long id) {
        UserCommunicationModel model = communicationRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.COMMUNICATION_NOT_FOUND));
        return populator.toDTO(model);
    }

    @Override
    public Page<UserCommunicationDTO> getCommunicationsByUser(Long userId, Pageable pageable) {
        Page<UserCommunicationModel> page = communicationRepository.findByCreatedById(userId, pageable);
        return page.map(populator::toDTO);
    }

    @Override
    public Page<UserCommunicationDTO> getCommunicationsByTypeAndStatus(CommunicationType type,
                                                                       CommunicationStatus status,
                                                                       Pageable pageable) {
        Page<UserCommunicationModel> page = communicationRepository.findByTypeAndStatus(type, status, pageable);
        return page.map(populator::toDTO);
    }
}
