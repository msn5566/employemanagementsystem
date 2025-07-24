package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyString;

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
        employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");

        employee2 = new Employee();
        employee2.setId("2");
        employee2.setName("Jane Smith");
        employee2.setContactInformation("jane.smith@example.com");
    }

    @Test
    void addEmployee_shouldSaveEmployeeAndReturnSavedEmployee() {
        when(employeeRepository.save(employee1)).thenReturn(employee1);

        Employee savedEmployee = employeeService.addEmployee(employee1);

        assertEquals(employee1, savedEmployee);
        verify(employeeRepository, times(1)).save(employee1);
    }

    @Test
    void getEmployeeById_shouldReturnEmployee_whenEmployeeExists() {
        String employeeId = "1";
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee1));

        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);

        assertTrue(employee.isPresent());
        assertEquals(employee1, employee.get());
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void getEmployeeById_shouldReturnEmptyOptional_whenEmployeeDoesNotExist() {
        String employeeId = "3";
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);

        assertFalse(employee.isPresent());
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void getAllEmployees_shouldReturnListOfAllEmployees() {
        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> allEmployees = employeeService.getAllEmployees();

        assertEquals(2, allEmployees.size());
        assertEquals(employees, allEmployees);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void updateEmployee_shouldUpdateEmployeeAndReturnUpdatedEmployee_whenEmployeeExists() {
        String employeeId = "1";
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Updated Name");
        updatedEmployee.setContactInformation("updated.email@example.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);

        Employee result = employeeService.updateEmployee(employeeId, updatedEmployee);

        assertEquals("Updated Name", employee1.getName());
        assertEquals("updated.email@example.com", employee1.getContactInformation());
        assertEquals(employee1, result);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).save(employee1);
    }

    @Test
    void updateEmployee_shouldThrowException_whenEmployeeDoesNotExist() {
        String employeeId = "3";
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Updated Name");
        updatedEmployee.setContactInformation("updated.email@example.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> employeeService.updateEmployee(employeeId, updatedEmployee));
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_shouldDeleteEmployee_whenEmployeeExists() {
        String employeeId = "1";
        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    public void testFindEmployeesByPhoto() throws IOException {
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        Employee employee = new Employee();
        employee.setId("1");
        employee.setName("Test Employee");
        employee.setContactInformation("test data");
        List<Employee> expectedEmployees = Collections.singletonList(employee);

        when(employeeRepository.findByContactInformation(anyString())).thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeService.findEmployeesByPhoto(photo);

        assertEquals(expectedEmployees, actualEmployees);
    }
}