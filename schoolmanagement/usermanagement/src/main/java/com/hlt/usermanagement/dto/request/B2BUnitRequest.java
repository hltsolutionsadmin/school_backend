package com.hlt.usermanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hlt.usermanagement.dto.MediaDTO;
import com.hlt.usermanagement.dto.enums.BusinessType;
import com.hlt.usermanagement.dto.response.BusinessAttributeResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class B2BUnitRequest {

    @NotBlank(message = "Business name is mandatory")
    private String businessName;

    private Long businessId;

    private String businessCode;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;

    @NotNull(message = "Business type is mandatory")
    private BusinessType businessType;

    @NotBlank(message = "Address Line 1 is mandatory")
    private String addressLine1;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String contactNumber;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean enabled;

    private String gstNumber;
    private String fssaiNo;

    private Boolean temporarilyClosed;

    private List<MultipartFile> mediaFiles;
    private List<String> mediaUrls;
    private List<MediaDTO> media;

    private Set<BusinessAttributeResponse> attributes;

    @NotBlank(message = "Admin username is mandatory")
    private String adminUsername;

    @NotBlank(message = "Admin full name is mandatory")
    private String adminFullName;

    @NotBlank(message = "Admin mobile is mandatory")
    private String adminMobile;

    @Email(message = "Admin email should be valid")
    private String adminEmail;

    private String adminPassword;


}
