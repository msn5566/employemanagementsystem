package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee("1", "John Doe", "IT", "Developer");
        employee2 = new Employee("2", "Jane Doe", "HR", "Manager");
    }

    @Test
    void searchEmployeesByName_shouldReturnMatchingEmployees() {
        // Arrange
        String searchName = "Doe";
        when(employeeRepository.findByNameContainingIgnoreCase(searchName)).thenReturn(Arrays.asList(employee1, employee2));

        // Act
        List<Employee> employees = employeeService.searchEmployeesByName(searchName);

        // Assert
        assertEquals(2, employees.size());
        assertEquals(employee1, employees.get(0));
        assertEquals(employee2, employees.get(1));
    }

    @Test
    void searchEmployeesByName_shouldReturnEmptyList_whenNoMatchingEmployeesFound() {
        // Arrange
        String searchName = "Smith";
        when(employeeRepository.findByNameContainingIgnoreCase(searchName)).thenReturn(Arrays.asList());

        // Act
        List<Employee> employees = employeeService.searchEmployeesByName(searchName);

        // Assert
        assertEquals(0, employees.size());
    }
}