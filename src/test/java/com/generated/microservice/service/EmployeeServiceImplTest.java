package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.MaterialIssue;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.repository.MaterialIssueRepository;
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

    @Mock
    private MaterialIssueRepository materialIssueRepository;

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
    void getMaterialsByEmployeeId_ExistingEmployeeId_ReturnsListOfMaterialIssues() {
        String employeeId = "123";
        MaterialIssue materialIssue = new MaterialIssue();
        materialIssue.setEmployeeId(employeeId);
        List<MaterialIssue> expectedMaterials = Collections.singletonList(materialIssue);

        when(materialIssueRepository.findByEmployeeId(employeeId)).thenReturn(expectedMaterials);

        List<MaterialIssue> actualMaterials = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(expectedMaterials, actualMaterials);
    }

    @Test
    void getMaterialsByEmployeeId_NonExistingEmployeeId_ReturnsEmptyList() {
        String employeeId = "456";

        when(materialIssueRepository.findByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        List<MaterialIssue> actualMaterials = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(Collections.emptyList(), actualMaterials);
    }
}