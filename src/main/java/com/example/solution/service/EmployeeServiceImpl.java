package com.example.solution.service;

import com.example.solution.entity.Employee;
import com.example.solution.repository.EmployeeRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepositoty repositoty;

    @Override
    public Long save(Employee employee) {
        repositoty.save(employee);
        return employee.getId();
    }

    @Override
    public Long update(Long id, Employee employee) {
        Employee tempEmployee = repositoty.getById(id);
        tempEmployee.setName(employee.getName());
        tempEmployee.setCv_file(employee.getCv_file());
        tempEmployee.setFlowId(employee.getFlowId());
        tempEmployee.setIdempotenceUuid(employee.getIdempotenceUuid());
        tempEmployee.setProcessId(employee.getProcessId());
        repositoty.update(tempEmployee);
        return tempEmployee.getId();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return repositoty.getById(id);
    }
}
