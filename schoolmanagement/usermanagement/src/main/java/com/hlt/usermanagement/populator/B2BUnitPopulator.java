package com.hlt.usermanagement.populator;


import com.hlt.usermanagement.dto.AddressDTO;
import com.hlt.usermanagement.dto.B2BUnitDTO;
import com.hlt.usermanagement.dto.UserDTO;
import com.hlt.usermanagement.model.B2BUnitModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class B2BUnitPopulator implements Populator<B2BUnitModel, B2BUnitDTO> {

	@Autowired
	private UserPopulator userPopulator;

	@Autowired
	private AddressPopulator addressPopulator;



	public B2BUnitDTO toDTO(B2BUnitModel source) {
		if (source == null)
			return null;
		B2BUnitDTO dto = new B2BUnitDTO();
		populate(source, dto);
		return dto;
	}

	@Override
	public void populate(B2BUnitModel source, B2BUnitDTO target) {
		if (source == null) {
			return;
		}

		target.setId(source.getId());
		target.setBusinessName(source.getBusinessName());
		target.setContactNumber(source.getContactNumber());
		target.setEnabled(source.getEnabled());
		target.setBusinessLatitude(source.getBusinessLatitude());
		target.setBusinessCode(source.getBusinessCode());
		target.setType(source.getType());
		target.setBusinessLongitude(source.getBusinessLongitude());


		if (source.getAdmin() != null) {
			UserDTO userDTO = new UserDTO();
			userPopulator.populate(source.getAdmin(), userDTO);
			target.setAdminUser(userDTO);
		}

		if (source.getBusinessAddress() != null) {
			AddressDTO addressDTO = new AddressDTO();
			addressPopulator.populate(source.getBusinessAddress(), addressDTO);
			target.setBusinessAddress(addressDTO);
		}



	}
}
