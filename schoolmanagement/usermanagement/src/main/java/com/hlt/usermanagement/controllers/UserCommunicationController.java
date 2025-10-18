package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.UserCommunicationDTO;
import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import com.hlt.usermanagement.services.UserCommunicationService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/communications")
@RequiredArgsConstructor
public class UserCommunicationController {

    private final UserCommunicationService userCommunicationService;


    @PostMapping
    public StandardResponse<UserCommunicationDTO> createCommunication(
            @Valid @RequestBody UserCommunicationDTO dto) {
        UserCommunicationDTO response = userCommunicationService.createCommunication(dto);
        return StandardResponse.single("Communication created successfully", response);
    }


    @GetMapping("/{id}")
    public StandardResponse<UserCommunicationDTO> getCommunicationById(@PathVariable Long id) {
        UserCommunicationDTO dto = userCommunicationService.getCommunicationById(id);
        return StandardResponse.single("Communication fetched successfully", dto);
    }

    @GetMapping("/user/{userId}")
    public StandardResponse<Page<UserCommunicationDTO>> getCommunicationsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserCommunicationDTO> result = userCommunicationService.getCommunicationsByUser(userId, pageable);
        return StandardResponse.page("User communications fetched successfully", result);
    }


    @GetMapping
    public StandardResponse<Page<UserCommunicationDTO>> getCommunicationsByTypeAndStatus(
            @RequestParam CommunicationType type,
            @RequestParam CommunicationStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserCommunicationDTO> result = userCommunicationService.getCommunicationsByTypeAndStatus(type, status, pageable);
        return StandardResponse.page("Communications fetched successfully", result);
    }
}
