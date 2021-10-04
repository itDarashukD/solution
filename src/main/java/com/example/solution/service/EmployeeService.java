package com.example.solution.service;

import com.example.solution.entity.Employee;
import org.springframework.stereotype.Service;


public interface EmployeeService {

    Long save(Employee employee);

    Employee findEmployeeById(Long id);

    Long  update(Long id, Employee employee);
}
