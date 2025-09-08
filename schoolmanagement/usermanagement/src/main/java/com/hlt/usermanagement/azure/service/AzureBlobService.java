package com.hlt.usermanagement.azure.service;

import com.hlt.usermanagement.model.MediaModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public interface AzureBlobService {

    MediaModel uploadFile(MultipartFile file) throws IOException;

    List<MediaModel> uploadFiles(List<MultipartFile> files) throws IOException;

    MediaModel uploadCustomerPictureFile(Long customerId, MultipartFile file, Long createdUser) throws IOException;
}
