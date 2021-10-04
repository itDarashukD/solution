package com.example.solution.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeSourceService {

    String save(MultipartFile file);

}
