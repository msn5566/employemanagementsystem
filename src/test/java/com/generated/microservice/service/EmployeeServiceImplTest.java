package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.Material;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.repository.MaterialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
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
    private MaterialRepository materialRepository;

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
        Material material = new Material();
        material.setId("material1");
        material.setMaterialName("Laptop");
        material.setIssueDate(LocalDate.now());
        material.setEmployeeId(employeeId);

        List<Material> materials = Collections.singletonList(material);
        when(materialRepository.findByEmployeeId(employeeId)).thenReturn(materials);

        List<MaterialDTO> materialDTOs = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(1, materialDTOs.size());
        assertEquals("Laptop", materialDTOs.get(0).getMaterialName());
        assertEquals(employeeId, materialDTOs.get(0).getEmployeeId());
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsNoMaterials() {
        String employeeId = "456";
        when(materialRepository.findByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        List<MaterialDTO> materialDTOs = employeeService.getMaterialsByEmployeeId(employeeId);

        assertEquals(0, materialDTOs.size());
    }
}