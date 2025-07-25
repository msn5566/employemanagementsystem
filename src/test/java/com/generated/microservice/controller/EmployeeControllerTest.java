package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void searchEmployeesByName_shouldReturnOkAndListOfEmployees() {
        // Arrange
        String name = "testName";
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        when(employeeService.findEmployeesByName(name)).thenReturn(employees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.searchEmployeesByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

    @Test
    void searchEmployeesByName_shouldReturnOkAndEmptyListIfNoEmployeesFound() {
        // Arrange
        String name = "nonExistingName";
        List<Employee> employees = new ArrayList<>();
        when(employeeService.findEmployeesByName(name)).thenReturn(employees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.searchEmployeesByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }
}