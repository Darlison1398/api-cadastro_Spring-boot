package com.src.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class ImagemService {

    private final String uploadDir = "C:\\Users\\Drsil\\OneDrive\\Documentos\\Back_end_java\\api-cadastro\\demo\\src\\main\\java\\com\\src\\uploads";

    public String saveImage(MultipartFile file) {
        try {
            // Verifica se o diretório de upload existe, senão, cria
            Path uploadPath = Path.of(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Gera um nome único para a imagem
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // Salva o arquivo no diretório
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (Exception e) {
             e.printStackTrace();
             return null;
        }
    }
}
