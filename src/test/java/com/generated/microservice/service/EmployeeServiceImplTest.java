package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void addEmployee_ValidInput_ReturnsEmployeeId() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setContactInformation("john.doe@example.com");

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        when(employeeRepository.save(employeeCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        String employeeId = employeeService.addEmployee(employeeDTO);

        // Assert
        assertNotNull(employeeId);
        verify(employeeRepository, times(1)).save(employeeCaptor.capture());
        Employee savedEmployee = employeeCaptor.getValue();

        assertEquals("John Doe", savedEmployee.getName());
        assertEquals("john.doe@example.com", savedEmployee.getContactInformation());
        assertEquals(employeeId, savedEmployee.getEmployeeId());
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsMaterials() {
        String employeeId = "123";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setContactInformation("Laptop, 2024-01-01, Dell XPS 15");

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Collections.singletonList(employee));

        List<MaterialDTO> materials = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(1, materials.size());
        assertEquals("Laptop", materials.get(0).getMaterialName());
        assertEquals("Dell XPS 15", materials.get(0).getDescription());
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsNoMaterials() {
        String employeeId = "456";
        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        List<MaterialDTO> materials = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(0, materials.size());
    }

    @Test
    void getMaterialsByEmployeeId_InvalidDate() {
        String employeeId = "789";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setContactInformation("Monitor, Invalid Date, Samsung 27 inch");

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Collections.singletonList(employee));

        List<MaterialDTO> materials = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(1, materials.size());
        assertEquals("Monitor", materials.get(0).getMaterialName());
        assertEquals("Samsung 27 inch", materials.get(0).getDescription());
    }

    @Test
    void getMaterialsByEmployeeId_InvalidContactInfo() {
        String employeeId = "999";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setContactInformation("Just one item");

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Collections.singletonList(employee));

        List<MaterialDTO> materials = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(0, materials.size());
    }
}