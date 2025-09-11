package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.SchoolDTO;
import com.hlt.usermanagement.model.SchoolModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class SchoolPopulator implements Populator<SchoolModel, SchoolDTO> {

    private final AddressPopulator addressPopulator;

    public SchoolPopulator(final AddressPopulator addressPopulator) {
        this.addressPopulator = addressPopulator;
    }

    @Override
    public void populate(SchoolModel source, SchoolDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCode(source.getCode());
        target.setContactNumber(source.getContactNumber());
        target.setEmail(source.getEmail());

        if (source.getAdmin() != null) {
            target.setAdminId(source.getAdmin().getId());
            target.setAdminName(source.getAdmin().getFullName());
        }

        if (source.getAddress() != null) {
            if (target.getAddress() == null) {
                target.setAddress(new com.hlt.usermanagement.dto.AddressDTO());
            }
            addressPopulator.populate(source.getAddress(), target.getAddress());
        }
    }

    public SchoolDTO toDTO(SchoolModel source) {
        if (source == null) return null;
        SchoolDTO dto = new SchoolDTO();
        populate(source, dto);
        return dto;
    }
}
