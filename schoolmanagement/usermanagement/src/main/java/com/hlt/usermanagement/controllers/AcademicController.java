package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.AcademicDTO;
import com.hlt.usermanagement.dto.AcademicUserDTO;
import com.hlt.usermanagement.dto.AssignUserDTO;
import com.hlt.usermanagement.services.AcademicService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/academics")
@RequiredArgsConstructor
public class AcademicController {

    private final AcademicService academicService;

    @PostMapping
    public StandardResponse<AcademicDTO> createAcademic(@Valid @RequestBody AcademicDTO dto) {
        AcademicDTO created = academicService.createAcademic(dto);
        return StandardResponse.single("Academic created successfully", created);
    }

    @GetMapping
    public StandardResponse<Page<AcademicDTO>> getAllAcademics(
            @RequestParam Long b2bUnitId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AcademicDTO> academics = academicService.getAllAcademics(b2bUnitId, pageable);
        return StandardResponse.page("Academics retrieved successfully", academics);
    }

    @PostMapping("/{academicId}/assign-users")
    public StandardResponse<Void> assignUsers(
            @PathVariable Long academicId,
            @Valid @RequestBody AssignUserDTO dto
    ) {
        academicService.assignUsers(academicId, dto);
        return StandardResponse.message("Users assigned successfully");
    }

    @GetMapping("/{academicId}/users")
    public StandardResponse<Page<AcademicUserDTO>> getUsersInAcademic(
            @PathVariable Long academicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AcademicUserDTO> users = academicService.getUsersInAcademic(academicId, pageable);
        return StandardResponse.page("Users in academic retrieved successfully", users);
    }

}
