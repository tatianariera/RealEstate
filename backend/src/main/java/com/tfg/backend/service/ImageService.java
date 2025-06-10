package com.tfg.backend.service;

import com.tfg.backend.entity.Image;
import com.tfg.backend.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    // Carpeta donde se guardarán las imágenes
    private static final String UPLOAD_DIR = "uploads/";

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String saveImage(MultipartFile file) throws IOException {
        // Crear la carpeta si no existe
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Guardar archivo
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.write(filePath, file.getBytes());

        // Guardar la URL en la base de datos
        Image image = new Image();
        image.setUrl("/" + UPLOAD_DIR + fileName); // puedes ajustar esta URL según tu frontend
        imageRepository.save(image);

        return image.getUrl();
    }
}
