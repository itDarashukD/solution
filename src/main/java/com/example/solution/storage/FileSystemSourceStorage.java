package com.example.solution.storage;

import com.example.solution.service.EmployeeSourceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class FileSystemSourceStorage implements EmployeeSourceService {

    @Value("${path.local.storage}")
    private String pathLocalStorage;

    @SneakyThrows
    @Override
    public String save(MultipartFile file) {

        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        Path path = Paths.get(pathLocalStorage, originalFilename);

        if (!Files.exists(path)) {
            log.info("IN FileSystemSourceStorage save() : file not exist as yet");
            try {
                log.info("IN FileSystemSourceStorage save() : coping file begin...");
                Files.copy(inputStream
                        , path
                        , StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.error("ERROR IN FileSystemSourceStorage save() :" + e.getMessage());
            }
        } else {
            log.info("File is exist now in this directory : " + pathLocalStorage + originalFilename);
        }
        return "File"+ originalFilename + "saved";
    }
}
