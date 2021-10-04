package com.example.solution.service;

import com.example.solution.entity.Employee;
import com.example.solution.repository.EmployeeRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepositoty repositoty;

    @Override
    public Long save(Employee employee) {
          repositoty.save(employee);
          return  employee.getId();
    }
}
