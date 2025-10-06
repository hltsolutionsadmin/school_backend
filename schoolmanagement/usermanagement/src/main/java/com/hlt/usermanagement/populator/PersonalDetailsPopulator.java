package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.PersonalDetailsDTO;
import com.hlt.usermanagement.model.PersonalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PersonalDetailsPopulator {

    public PersonalDetails toEntity(PersonalDetailsDTO dto) {
        return PersonalDetails.builder()
                .fatherName(dto.getFatherName())
                .motherName(dto.getMotherName())
                .dateOfBirth(dto.getDateOfBirth())
                .age(dto.getAge())
                .bloodGroup(dto.getBloodGroup())
                .mobileNumber(dto.getMobileNumber())
                .build();
    }

    public PersonalDetailsDTO toDTO(PersonalDetails entity) {
        return PersonalDetailsDTO.builder()
                .fatherName(entity.getFatherName())
                .motherName(entity.getMotherName())
                .dateOfBirth(entity.getDateOfBirth())
                .age(entity.getAge() != null ? entity.getAge() : 0)
                .bloodGroup(entity.getBloodGroup())
                .mobileNumber(entity.getMobileNumber())
                .build();
    }

    public void updateEntity(PersonalDetailsDTO dto, PersonalDetails entity) {
        if (dto.getFatherName() != null) {
            entity.setFatherName(dto.getFatherName());
        }
        if (dto.getMotherName() != null) {
            entity.setMotherName(dto.getMotherName());
        }
        if (dto.getDateOfBirth() != null) {
            entity.setDateOfBirth(dto.getDateOfBirth());
        }
        if (dto.getAge() > 0) {
            entity.setAge(dto.getAge());
        }
        if (dto.getBloodGroup() != null) {
            entity.setBloodGroup(dto.getBloodGroup());
        }
        if (dto.getMobileNumber() != null) {
            entity.setMobileNumber(dto.getMobileNumber());
        }
    }
}
