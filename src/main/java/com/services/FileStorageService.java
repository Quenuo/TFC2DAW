package com.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


//esta clase es la que se encarga de descargar la imagen por url
@Service
public class FileStorageService {
    private static final String DINOSAUR_UPLOAD_DIR = "resources"+ File.separator+"static"+File.separator+"images"+File.separator+"dinosaurs"+File.separator;
    private static final String ENCLOSURE_UPLOAD_DIR = "resources"+ File.separator+"static"+File.separator+"images"+File.separator+"enclosures"+File.separator;

    public String saveFile(MultipartFile file, String type) throws IOException {
        String uploadDir = type.equals("dinosaur") ? DINOSAUR_UPLOAD_DIR : ENCLOSURE_UPLOAD_DIR;
        Files.createDirectories(Paths.get(uploadDir));
        String filePath = uploadDir + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filePath;
    }
}
