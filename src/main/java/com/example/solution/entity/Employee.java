package com.example.solution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;
    private String name;
    private String processId;
    private String flowId;
    private String idempotenceUuid;
    private String cv_file;

    public Employee(String name, String processId, String flowId, String idempotenceUuid, String cv_file) {
        this.name = name;
        this.processId = processId;
        this.flowId = flowId;
        this.idempotenceUuid = idempotenceUuid;
        this.cv_file = cv_file;
    }
}
