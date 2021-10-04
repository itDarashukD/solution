package com.example.solution.controller;

import com.example.solution.entity.Employee;
import com.example.solution.service.EmployeeService;
import com.example.solution.service.EmployeeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/employee")
@RestController
public class EmployeeController {

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

        Employee employee = new Employee(name, processId, flowId, idempotenceUuid, cv_file.getOriginalFilename());
        employeeService.save(employee);
        sourceService.save(cv_file);
        return "ok!";
    }

    @PostMapping("/update/{id}")
    public Long updateEmployee(
            @PathVariable("id") Long id,
            @RequestBody Employee employee) {
        return employeeService.update(id, employee);

    }

    @GetMapping(value = "/getEmployee/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }
}
