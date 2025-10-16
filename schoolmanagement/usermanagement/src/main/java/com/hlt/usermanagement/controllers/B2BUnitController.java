package com.hlt.usermanagement.controllers;


import com.hlt.usermanagement.dto.AddressDTO;
import com.hlt.usermanagement.dto.B2BUnitDTO;
import com.hlt.usermanagement.dto.B2BUnitStatusDTO;
import com.hlt.usermanagement.dto.request.B2BUnitRequest;
import com.hlt.usermanagement.dto.response.B2BUnitListResponse;
import com.hlt.usermanagement.populator.B2BUnitPopulator;
import com.hlt.usermanagement.populator.UserPopulator;
import com.hlt.usermanagement.services.B2BUnitService;
import com.hlt.usermanagement.services.MediaService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import com.schoolmanagement.commonservice.user.UserDetailsImpl;
import com.schoolmanagement.utils.SRAppConstants;
import com.schoolmanagement.utils.SecurityUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/business")
@Slf4j
@AllArgsConstructor
public class B2BUnitController {

    private final B2BUnitService b2BUnitService;
    private final B2BUnitPopulator b2BUnitPopulator;
    private final UserPopulator userPopulator;
    private final MediaService mediaService;

    @PreAuthorize(SRAppConstants.ROLE_SUPER_ADMIN)
    @PostMapping("/onboard")
    public ResponseEntity<B2BUnitDTO> createB2BUnit( @RequestBody B2BUnitRequest request) throws IOException {
        B2BUnitDTO response = b2BUnitService.createOrUpdate(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse<Page<B2BUnitListResponse>>> listBusinesses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<B2BUnitListResponse> businesses = b2BUnitService.listAllPaginated(page, size);
        return ResponseEntity.ok(StandardResponse.page("Businesses fetched successfully", businesses));
    }


    @GetMapping("/{id}")
    public ResponseEntity<B2BUnitDTO> getBusinessById(@PathVariable Long id) {
        return ResponseEntity.ok(b2BUnitService.getById(id));
    }

    @GetMapping("get")
    public ResponseEntity<B2BUnitDTO> getBusinessByToken() {
        log.info("Received request to fetch B2B unit details by token");
        UserDetailsImpl loggedInUser = SecurityUtils.getCurrentUserDetails();
        log.debug("Extracted user details: userId={}", loggedInUser.getId());
        B2BUnitDTO response = b2BUnitService.getById(loggedInUser.getId());
        log.info("Successfully retrieved B2B unit for userId={}", loggedInUser.getId());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/status")
    public ResponseEntity<List<B2BUnitStatusDTO>> getBusinessNameAndApprovalStatusForLoggedInUser() {
        List<B2BUnitStatusDTO> b2bUnitStatusList = b2BUnitService.getBusinessNameAndApprovalStatusForLoggedInUser();
        return ResponseEntity.ok(b2bUnitStatusList);
    }

//    @GetMapping("/searchbycity")
//    public ResponseEntity<StandardResponse<Page<B2BUnitDTO>>> searchByCity(
//            @RequestParam String city,
//            @RequestParam String categoryName,
//            @RequestParam(required = false) String searchTerm,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        log.info("Searching B2B units by city={}, categoryName={}, searchTerm={}", city, categoryName, searchTerm);
//        Pageable pageable = PageRequest.of(page, size);
//        Page<B2BUnitDTO> result = b2BUnitService.searchByCityAndCategory(city, categoryName, searchTerm, pageable);
//        return ResponseEntity.ok(StandardResponse.page("B2B units fetched successfully", result));
//    }


    @GetMapping("/business/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable("id") Long unitId) {
        AddressDTO addressDTO = b2BUnitService.getAddressByB2BUnitId(unitId);
        return ResponseEntity.ok(addressDTO);
    }


}





