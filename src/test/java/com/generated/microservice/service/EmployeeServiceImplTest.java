package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void findEmployeesByPhoto_returnsAllEmployees() {
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findEmployeesByPhoto(photo);

        assertEquals(2, result.size());
    }

    @Test
    void findEmployeesByPhoto_returnsEmptyList() {
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());
        List<Employee> employees = new ArrayList<>();

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findEmployeesByPhoto(photo);

        assertEquals(0, result.size());
    }
}