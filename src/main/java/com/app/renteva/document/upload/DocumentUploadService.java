package com.app.renteva.document.upload;

import com.app.renteva.shared.storage.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentUploadService extends FileStorageService {

    String saveAttachment(MultipartFile file, String placeCode);
}
