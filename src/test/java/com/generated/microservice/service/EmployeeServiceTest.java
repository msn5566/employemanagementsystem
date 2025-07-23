package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId("1");
        employee.setName("John Doe");
        employee.setRole("Developer");
        employee.setDepartment("IT");
    }

    @Test
    void findEmployeesByName_shouldReturnListOfEmployees_whenNameExists() {
        // Arrange
        String name = "John Doe";
        when(employeeRepository.findByName(name)).thenReturn(Collections.singletonList(employee));

        // Act
        List<Employee> employees = employeeService.findEmployeesByName(name);

        // Assert
        assertEquals(1, employees.size());
        assertEquals(name, employees.get(0).getName());
    }

    @Test
    void findEmployeesByName_shouldReturnEmptyList_whenNameDoesNotExist() {
        // Arrange
        String name = "NonExistingName";
        when(employeeRepository.findByName(name)).thenReturn(Collections.emptyList());

        // Act
        List<Employee> employees = employeeService.findEmployeesByName(name);

        // Assert
        assertEquals(0, employees.size());
    }
}