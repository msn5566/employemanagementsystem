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
        employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("John Doe");
        employee1.setDepartment("IT");
        employee1.setEmail("john.doe@example.com");

        employee2 = new Employee();
        employee2.setId("2");
        employee2.setName("Jane Doe");
        employee2.setDepartment("HR");
        employee2.setEmail("jane.doe@example.com");
    }

    @Test
    void findByName_shouldReturnListOfEmployees_whenNameExists() {
        when(employeeRepository.findByName("John Doe")).thenReturn(Arrays.asList(employee1));

        List<Employee> employees = employeeService.findByName("John Doe");

        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void getAllEmployees_shouldReturnListOfAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("Jane Doe", employees.get(1).getName());
    }

    @Test
    void getEmployeeById_shouldReturnEmployee_whenIdExists() {
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee1));

        Optional<Employee> employee = employeeService.getEmployeeById("1");

        assertTrue(employee.isPresent());
        assertEquals("John Doe", employee.get().getName());
    }

    @Test
    void createEmployee_shouldSaveEmployee() {
        when(employeeRepository.save(employee1)).thenReturn(employee1);

        Employee createdEmployee = employeeService.createEmployee(employee1);

        assertEquals("John Doe", createdEmployee.getName());
    }

    @Test
    void updateEmployee_shouldUpdateEmployee_whenIdExists() {
        Employee employeeDetails = new Employee();
        employeeDetails.setName("Updated Name");
        employeeDetails.setDepartment("Updated Department");
        employeeDetails.setEmail("updated.email@example.com");

        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(employee1)).thenReturn(employee1);

        Employee updatedEmployee = employeeService.updateEmployee("1", employeeDetails);

        assertEquals("Updated Name", updatedEmployee.getName());
        assertEquals("Updated Department", updatedEmployee.getDepartment());
        assertEquals("updated.email@example.com", updatedEmployee.getEmail());
    }

    @Test
    void deleteEmployee_shouldDeleteEmployee_whenIdExists() {
        String employeeId = "123";
        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}