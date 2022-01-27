package com.app.renteva.document.upload;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DocumentUploadServiceImpl implements DocumentUploadService {

    Path placeRoot = Paths.get("uploads/places");
    Path root = Paths.get("offers/attachments");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(@NotNull MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public String saveAttachment(MultipartFile file, String placeCode) {
        try {
            final Path placeFolder = placeRoot.resolve(placeCode);
            final Path attachmentsFolder = placeFolder.resolve(root);

            if (!Files.exists(attachmentsFolder))
                Files.createDirectories(attachmentsFolder);

            if (!Files.exists(attachmentsFolder.resolve(file.getOriginalFilename())))
                Files.copy(file.getInputStream(), attachmentsFolder.resolve(Objects.requireNonNull(file.getOriginalFilename())));

            final String pathReference = placeFolder + File.separator + root;
            return Paths.get(pathReference + File.separator + file.getOriginalFilename()).toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
