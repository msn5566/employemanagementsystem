package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void findEmployeesByName_shouldReturnListOfEmployees() {
        // Arrange
        String name = "testName";
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        when(employeeRepository.findByNameIgnoreCase(name)).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.findEmployeesByName(name);

        // Assert
        assertEquals(employees, result);
    }

    @Test
    void findEmployeesByName_shouldReturnEmptyListIfNoEmployeesFound() {
        // Arrange
        String name = "nonExistingName";
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.findByNameIgnoreCase(name)).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.findEmployeesByName(name);

        // Assert
        assertEquals(employees, result);
    }
}