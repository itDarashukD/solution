package com.example.solution.service;

import com.example.solution.entity.Employee;
import com.example.solution.repository.EmployeeRepositoty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private final Employee employee1 = new Employee(1L, "name1", "1", "1"
                                                                , "1", "cv1");

    @Mock
    private EmployeeRepositoty repositoty;

    @InjectMocks
    private EmployeeServiceImpl service;


    @Test
    void save() {
        service.save(employee1);
        verify(repositoty, times(1)).save(any());
    }

    @Test
    void update() {
        when(repositoty.getById(anyLong())).thenReturn(employee1);
        employee1.setName("testName");
        employee1.setFlowId("2");
        employee1.setIdempotenceUuid("2");
        service.update(employee1.getId(),employee1);
        verify(repositoty,times(1)).update(employee1);
        assertEquals(employee1.getName(),"testName");
    }

    @Test
    void findEmployeeById() {
        when(repositoty.getById(anyLong())).thenReturn(employee1);
        assertEquals(service.findEmployeeById(1L),employee1);
        verify(repositoty,times(1)).getById(1L);
    }
}