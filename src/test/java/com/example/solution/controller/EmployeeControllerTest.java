package com.example.solution.controller;

import com.example.solution.SolutionApplication;
import com.example.solution.entity.Employee;
import com.example.solution.service.EmployeeService;
import com.example.solution.service.EmployeeSourceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ContextConfiguration(classes = SolutionApplication.class)
@WebMvcTest(value = EmployeeController.class)
@ActiveProfiles("test")
class EmployeeControllerTest {

    private final Employee employee1 = new Employee(1L,"name1","1","1","1","cv1");
    private final String EXPECTED_CONTENT = "1";
    private final MockMultipartFile cv_file = new MockMultipartFile(
                                                                   "cv_file",
                                                                   "hello.txt",
                                                                    MediaType.TEXT_PLAIN_VALUE,
                                                                    "Hello, World!".getBytes()
    );

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private EmployeeSourceService sourceService;

    @Test
    void saveFileWithMetadata() throws Exception {
        mockMvc.perform(multipart("/employee/save/name1?flowId=1&idempotenceUuid=1&processId=1")
                .file(cv_file))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("ok!"));
    }

    @Test
    void updateEmployee() throws Exception {
        when(employeeService.update(1L, employee1)).thenReturn((employee1.getId()));
        mockMvc.perform(post("/employee/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(EXPECTED_CONTENT));
    }

    @Test
    void findById() throws Exception {
        when(employeeService.findEmployeeById(anyLong()))
                .thenReturn(employee1);
        this.mockMvc.perform(get("/employee/getEmployee/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("name1")));
    }
}