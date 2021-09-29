package com.app.renteva.place.photo;

import com.app.renteva.shared.storage.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoUploadService extends FileStorageService {

    String saveWithPlaceReference(MultipartFile file, String reference);
}
