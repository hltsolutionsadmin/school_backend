package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.azure.service.AzureBlobService;
import com.hlt.usermanagement.dto.BasicUserDetails;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.MediaPopulator;
import com.hlt.usermanagement.services.UserService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.commonservice.dto.BasicOnboardUserDTO;
import com.schoolmanagement.commonservice.dto.LoggedInUser;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import com.schoolmanagement.commonservice.dto.UserDTO;
import com.schoolmanagement.commonservice.enums.ERole;
import com.schoolmanagement.commonservice.user.UserDetailsImpl;
import com.schoolmanagement.utils.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final AzureBlobService azureBlobService;
    private final MediaPopulator mediaPopulator;

    @GetMapping("/find/{userId}")
    public StandardResponse<UserDTO> getUserById(@PathVariable("userId") Long userId) {
        UserDTO dto = userService.getUserById(userId);
        return StandardResponse.single("User fetched successfully", dto);
    }

    @GetMapping("/userDetails")
    public StandardResponse<UserDTO> getUserByToken() {
        UserDetailsImpl user = SecurityUtils.getCurrentUserDetails();
        UserDTO dto = userService.getUserById(user.getId());
        return StandardResponse.single("User fetched successfully", dto);
    }

    @PostMapping("/details/all")
    public List<UserModel> getUserDetailsByIds(@RequestBody List<Long> userIds) {
        return userService.findByIds(userIds);
    }

    @PutMapping("/role/{role}")
    public StandardResponse<String> assignRoleToCurrentUser(@PathVariable String role) {
        UserDetailsImpl currentUser = SecurityUtils.getCurrentUserDetails();
        try {
            ERole parsedRole = ERole.valueOf(role.toUpperCase());
            userService.addUserRole(currentUser.getId(), parsedRole);
            return StandardResponse.single("Role successfully added", parsedRole.name());
        } catch (IllegalArgumentException e) {
            throw new HltCustomerException(ErrorCode.INVALID_ROLE);
        }
    }

    @PutMapping("/userDetails")
    public StandardResponse<String> updateBasicUserDetails(@Valid @RequestBody BasicUserDetails details) {
        UserDetailsImpl loggedInUser = SecurityUtils.getCurrentUserDetails();
        UserModel user = userService.findById(loggedInUser.getId());

        if (user == null) {
            throw new HltCustomerException(ErrorCode.USER_NOT_FOUND);
        }

        if (StringUtils.hasText(details.getFullName())) {
            user.setFullName(details.getFullName());
        }

        if (StringUtils.hasText(details.getEmail())) {
            if (!userService.existsByEmail(details.getEmail(), user.getId())) {
                user.setEmail(details.getEmail());
            } else {
                throw new HltCustomerException(ErrorCode.EMAIL_ALREADY_IN_USE);
            }
        }

        if (details.getGender() != null) user.setGender(details.getGender());
        if (details.getFcmToken() != null) user.setFcmToken(details.getFcmToken());

        userService.saveUser(user);
        return StandardResponse.message("User details updated successfully");
    }

    @PostMapping("/onboard-with-credentials")
    public StandardResponse<Long> onBoardUserWithCredentials(@Valid @RequestBody BasicOnboardUserDTO dto) {
        Long userId = userService.onBoardUserWithCredentials(dto);
        return StandardResponse.single("User onboarded successfully", userId);
    }

    @DeleteMapping("/contact/{mobileNumber}/role/{role}")
    public StandardResponse<String> removeUserRole(@PathVariable String mobileNumber,
                                                   @PathVariable ERole role) {
        userService.removeUserRole(mobileNumber, role);
        return StandardResponse.message("Role successfully removed from user");
    }

    @GetMapping("/contact")
    public StandardResponse<LoggedInUser> getByPrimaryContact(@Valid @RequestParam("primaryContact") String primaryContact) {
        UserModel user = userService.findByPrimaryContact(primaryContact)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setId(user.getId());
        loggedInUser.setFullName(user.getFullName());
        loggedInUser.setPrimaryContact(user.getPrimaryContact());
        return StandardResponse.single("User fetched successfully", loggedInUser);
    }

//    @SuppressWarnings("unchecked")
//    @PostMapping("/profile/upload")
//    public ResponseEntity<MediaDTO> uploadCustomerProfilePicture(@ModelAttribute MultipartFile profilePicture)
//            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        log.info("Entering uploadCustomerProfilePicture API");
//        UserDetailsImpl loggedInUser = SecurityUtils.getCurrentUserDetails();
//
//        if (!ObjectUtils.isEmpty(loggedInUser)) {
//            MediaModel mediaModel = azureBlobService.uploadCustomerPictureFile(loggedInUser.getId(), profilePicture,
//                    loggedInUser.getId());
//            MediaDTO mediaDTO = (MediaDTO) getConvertedInstance().convert(mediaModel);
//            log.info("Profile picture uploaded successfully for user with ID {}", loggedInUser.getId());
//            return new ResponseEntity<>(mediaDTO, HttpStatus.OK);
//        }
//        log.error("LoggedInUser not found or invalid");
//        return new ResponseEntity<>(new MediaDTO(), HttpStatus.BAD_REQUEST);
//    }

//    @SuppressWarnings("unchecked")
//    public AbstractConverter getConvertedInstance() {
//        return getConverter(mediaPopulator, MediaDTO.class.getName());
//    }

    @GetMapping("/byRole")
    public StandardResponse<List<UserDTO>> getUsersByRole(@RequestParam String roleName) {
        List<UserDTO> dtos = userService.getUsersByRole(roleName);
        return StandardResponse.list("Users fetched successfully", dtos);
    }

    @DeleteMapping("/fcmToken")
    public StandardResponse<String> deleteFcmToken() {
        UserDetailsImpl loggedInUser = SecurityUtils.getCurrentUserDetails();
        userService.clearFcmToken(loggedInUser.getId());
        return StandardResponse.message("FCM token deleted successfully");
    }

    //TODO : pending get getUserCountBySchool

//    @GetMapping("/count/school/{schoolId}")
//    public StandardResponse<Long> getUserCountBySchool(@PathVariable Long schoolId) {
//        long count = userService.getUserCountBySchoolId(schoolId);
//        return StandardResponse.single("User count fetched successfully", count);
//    }

}



