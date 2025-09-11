package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.model.MediaModel;
import com.hlt.usermanagement.model.RoleModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.repository.SchoolRepository;
import com.schoolmanagement.commonservice.dto.Role;
import com.schoolmanagement.auth.UserServiceAdapter;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.commonservice.dto.BasicOnboardUserDTO;
import com.schoolmanagement.commonservice.dto.MediaDTO;
import com.schoolmanagement.commonservice.dto.UserDTO;
import com.schoolmanagement.commonservice.enums.ERole;
import com.schoolmanagement.commonservice.user.UserDetailsImpl;
import com.hlt.usermanagement.dto.UserUpdateDTO;
import com.hlt.usermanagement.repository.MediaRepository;
import com.hlt.usermanagement.repository.RoleRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.UserService;
import com.schoolmanagement.utils.SecurityUtils;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserServiceAdapter {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CaffeineCacheManager cacheManager;
    private final MediaRepository mediaRepository;
    private final PasswordEncoder passwordEncoder;
    private final SchoolRepository schoolRepository;

    @Override
    public UserModel saveUser(UserModel userModel) {
        try {
            return userRepository.save(userModel);
        } catch (Exception ex) {
            log.error("Failed to save user: {}", userModel, ex);
            throw ex;
        }
    }


    @Override
    public Long onBoardUserWithCredentials(BasicOnboardUserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new HltCustomerException(ErrorCode.USER_ALREADY_EXISTS);
        }

        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setPrimaryContact(dto.getPrimaryContact());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(fetchRoles(dto.getUserRoles()));
        user.setSchool(fetchSchoolById(dto.getSchoolId()));

        return saveUser(user).getId();
    }

    @Override
    public void updateUser(UserUpdateDTO details, Long userId) {
        UserDetailsImpl currentUser = SecurityUtils.getCurrentUserDetails();
        UserModel user = getUserByIdOrThrow(currentUser.getId());

        if (!Objects.equals(userId, user.getId())) {
            throw new HltCustomerException(ErrorCode.USER_NOT_FOUND);
        }

        if (existsByEmail(details.getEmail(), userId)) {
            throw new HltCustomerException(ErrorCode.EMAIL_ALREADY_IN_USE);
        }

        user.setEmail(details.getEmail());
        user.setFullName(details.getFullName());
        saveUser(user);
    }

    @Override
    public Long onBoardUser(String fullName, String mobileNumber, Set<ERole> userRoles, Long schoolId) {
        Optional<UserModel> existingUserOpt = findByPrimaryContact(mobileNumber);
        if (existingUserOpt.isPresent()) {
            return existingUserOpt.get().getId();
        }


        UserModel user = new UserModel();
        user.setPrimaryContact(mobileNumber);
        user.setRoles(fetchRoles(userRoles));
        user.setFullName(fullName);
         user.setSchool(fetchSchoolById(schoolId));

        return saveUser(user).getId();
    }

    private SchoolModel fetchSchoolById(Long schoolId) {
        return schoolRepository.findById(schoolId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));
    }
    @Override
    public void addUserRole(Long userId, ERole userRole) {
        UserModel user = getUserByIdOrThrow(userId);
        RoleModel role = getRoleByEnum(userRole);

        if (user.getRoles().add(role)) {
            saveUser(user);
        }
    }

    @Override
    public void removeUserRole(String mobileNumber, ERole userRole) {
        UserModel user = findByPrimaryContact(mobileNumber)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
        RoleModel role = getRoleByEnum(userRole);

        if (!user.getRoles().remove(role)) {
            throw new HltCustomerException(ErrorCode.ROLE_NOT_FOUND);
        }

        saveUser(user);
    }

    @Override
    @Transactional
    public UserDTO getUserById(Long userId) {
        UserModel user = getUserByIdOrThrow(userId);
        UserDTO dto = convertToUserDto(user);

        List<MediaDTO> mediaList = mediaRepository.findByCustomerId(userId)
                .stream()
                .map(this::convertToMediaDto)
                .toList();
        dto.setMedia(mediaList);

        return dto;
    }

    @Override
    public UserModel findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<UserModel> findByIds(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public UserModel findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }

        String emailHash = DigestUtils.sha256Hex(email.trim().toLowerCase());
        return userRepository.findByEmailHash(emailHash).orElse(null);
    }


    @Override
    public Optional<UserModel> findByPrimaryContact(String primaryContact) {
        return userRepository.findByPrimaryContactHash(DigestUtils.sha256Hex(primaryContact));

    }


    @Override
    public List<UserDTO> getUsersByRole(String roleName) {
        ERole role = ERole.valueOf(roleName.toUpperCase());
        RoleModel roleModel = getRoleByEnum(role);

        return userRepository.findByRolesContaining(roleModel)
                .stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }



    @Override
    @Transactional
    public void clearFcmToken(Long userId) {
        UserModel user = getUserByIdOrThrow(userId);
        user.setFcmToken(null);
        userRepository.save(user);
    }



    @Override
    public Optional<UserModel> findByUsername(@NotBlank String username) {
        return userRepository.findByUsername(username);
    }
    private void updateCache(Long userId, UserModel userModel) {
        UserDTO dto = convertToUserDto(userModel);
        Cache userCache = cacheManager.getCache("users");
        if (userCache != null) {
            userCache.put(userId, dto);
        }
    }

    private RoleModel getRoleByEnum(ERole role) {
        return roleRepository.findByName(role)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ROLE_NOT_FOUND));
    }

    private Set<RoleModel> fetchRoles(Set<ERole> roles) {
        return roleRepository.findByNameIn(roles);
    }

    private UserModel getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
    }

    private MediaDTO convertToMediaDto(MediaModel media) {
        MediaDTO dto = new MediaDTO();
        dto.setId(media.getId());
        dto.setUrl(media.getUrl());
        dto.setName(media.getFileName());
        dto.setDescription(media.getDescription());
        dto.setExtension(media.getExtension());
        dto.setCreationTime(media.getCreationTime());
        dto.setMediaType(media.getMediaType());
        return dto;
    }

    public UserDTO convertToUserDto(UserModel user) {
        Set<Role> roles = user.getRoles().stream()
                .map(role -> new Role(role.getId(), role.getName()))
                .collect(Collectors.toSet());

        String profilePicture = Optional.ofNullable(
                        mediaRepository.findByCustomerIdAndMediaType(user.getId(), "PROFILE_PICTURE"))
                .map(MediaModel::getUrl)
                .orElse(null);



        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .primaryContact(user.getPrimaryContact())
                .email(user.getEmail())
                .token(user.getFcmToken())
                .username(user.getUsername())
                .gender(user.getGender())
                .profilePicture(profilePicture)
                .roles(roles)
                .password(user.getPassword())
                .profileCompleted(user.getProfileCompleted())
                .build();
    }



    public Boolean existsByEmail(String email, Long userId) {
        return userRepository.existsByEmailAndNotUserId(email, userId);
    }
}


