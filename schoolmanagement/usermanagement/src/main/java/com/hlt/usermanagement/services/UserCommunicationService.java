package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.UserCommunicationDTO;
import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserCommunicationService {

    UserCommunicationDTO createCommunication(UserCommunicationDTO dto);


    UserCommunicationDTO getCommunicationById(Long id);


    Page<UserCommunicationDTO> getCommunicationsByUser(Long userId, Pageable pageable);


    Page<UserCommunicationDTO> getCommunicationsByTypeAndStatus(CommunicationType type,
                                                                CommunicationStatus status,
                                                                Pageable pageable);
}
