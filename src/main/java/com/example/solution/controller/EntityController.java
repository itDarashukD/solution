package com.example.solution.controller;

import com.example.solution.entity.Employee;
import com.example.solution.service.EmployeeService;
import com.example.solution.service.EmployeeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequestMapping("/song")
@RestController
public class EntityController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeSourceService sourceService;

    @PostMapping("/save/{name}")
    public String saveFileWithMetadata(
            @PathVariable("name") String name,
            @RequestParam("processId") String processId,
            @RequestParam("flowId") String flowId,
            @RequestParam("idempotenceUuid") String idempotenceUuid,
            @RequestParam("cv_file") MultipartFile cv_file) {

        Employee employee = new Employee(name,processId,flowId,idempotenceUuid, cv_file.getOriginalFilename());
        employeeService.save(employee);
        sourceService.save(cv_file);

        return "ok!";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(
            @PathVariable("id") Long id,                           //update Employee
            @RequestBody Employee employee) {                         //update Employee

        //придумать реализацию этого контроллера
        return "ok!";
    }
}
