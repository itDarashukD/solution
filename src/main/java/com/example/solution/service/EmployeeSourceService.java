package com.example.solution.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface EmployeeSourceService {

    String save(MultipartFile file);

}
