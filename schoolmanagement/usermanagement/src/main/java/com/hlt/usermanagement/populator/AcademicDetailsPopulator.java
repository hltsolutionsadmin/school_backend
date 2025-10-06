package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.AcademicDetailsDTO;
import com.hlt.usermanagement.model.AcademicDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AcademicDetailsPopulator {

    public AcademicDetails toEntity(AcademicDetailsDTO dto) {
        return AcademicDetails.builder()
                .overallRating(dto.getOverallRating())
                .examinationsRating(dto.getExaminationsRating())
                .detail3Rating(dto.getDetail3Rating())
                .detail4Rating(dto.getDetail4Rating())
                .detail5Rating(dto.getDetail5Rating())
                .build();
    }


    public AcademicDetailsDTO toDTO(AcademicDetails entity) {
        return AcademicDetailsDTO.builder()
                .overallRating(entity.getOverallRating())
                .examinationsRating(entity.getExaminationsRating())
                .detail3Rating(entity.getDetail3Rating())
                .detail4Rating(entity.getDetail4Rating())
                .detail5Rating(entity.getDetail5Rating())
                .build();
    }


    public void updateEntity(AcademicDetailsDTO dto, AcademicDetails entity) {
        if (dto.getOverallRating() != null) {
            entity.setOverallRating(dto.getOverallRating());
        }
        if (dto.getExaminationsRating() != null) {
            entity.setExaminationsRating(dto.getExaminationsRating());
        }
        if (dto.getDetail3Rating() != null) {
            entity.setDetail3Rating(dto.getDetail3Rating());
        }
        if (dto.getDetail4Rating() != null) {
            entity.setDetail4Rating(dto.getDetail4Rating());
        }
        if (dto.getDetail5Rating() != null) {
            entity.setDetail5Rating(dto.getDetail5Rating());
        }
    }
}
