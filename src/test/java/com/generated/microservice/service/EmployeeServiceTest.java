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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee("1", "John", "Doe", "john.doe@example.com", "IT");
        employee2 = new Employee("2", "Jane", "Smith", "jane.smith@example.com", "HR");
    }

    @Test
    void findEmployeesByName_shouldReturnListOfEmployees() {
        when(employeeRepository.findByFirstName("John")).thenReturn(Arrays.asList(employee1));

        List<Employee> employees = employeeService.findEmployeesByName("John");

        assertEquals(1, employees.size());
        assertEquals("John", employees.get(0).getFirstName());
    }

    @Test
    void createEmployee_shouldReturnCreatedEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);

        Employee createdEmployee = employeeService.createEmployee(employee1);

        assertEquals("John", createdEmployee.getFirstName());
    }

    @Test
    void getEmployeeById_shouldReturnEmployee() {
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee1));

        Optional<Employee> employee = employeeService.getEmployeeById("1");

        assertTrue(employee.isPresent());
        assertEquals("John", employee.get().getFirstName());
    }

    @Test
    void updateEmployee_shouldReturnUpdatedEmployee() {
        Employee employeeDetails = new Employee("1", "UpdatedJohn", "Doe", "john.doe@example.com", "IT");
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeDetails);

        Employee updatedEmployee = employeeService.updateEmployee("1", employeeDetails);

        assertEquals("UpdatedJohn", updatedEmployee.getFirstName());
    }

    @Test
    void deleteEmployee_shouldDeleteEmployee() {
        String employeeId = "1";
        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}