package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.model.MediaModel;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.hlt.usermanagement.dto.MediaDTO;
import com.hlt.usermanagement.populator.MediaPopulator;
import com.hlt.usermanagement.repository.MediaRepository;
import com.hlt.usermanagement.services.MediaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final MediaPopulator mediaPopulator;


    @Override
    @Transactional
    public MediaModel saveMedia(MediaModel mediaModel) {
        return mediaRepository.save(mediaModel);
    }

    @Override
    public MediaModel findByJtcustomerAndMediaType(Long userId, String mediaType) {
        return mediaRepository.findByCustomerIdAndMediaType(userId, mediaType);
    }

    @Override
    public void uploadMedia(Long b2bUnitId, MediaDTO dto) {

        MediaModel media = new MediaModel();
        media.setUrl(dto.getUrl());
        media.setFileName(dto.getFileName());
        media.setMediaType(dto.getMediaType());
        media.setDescription(dto.getDescription());
        media.setExtension(dto.getExtension());
        media.setActive(dto.isActive());
        media.setCreatedBy(dto.getCreatedBy());
        media.setCustomerId(dto.getCustomerId());

        mediaRepository.save(media);
    }
}


