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

import java.util.Arrays;
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
    private MaterialRepository materialRepository;

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
    public void testGetMaterialsByEmployeeId() {
        // Arrange
        String employeeId = "123";
        Material material1 = new Material();
        material1.setItemName("Laptop");
        material1.setIssueDate("2024-01-01");
        material1.setNotes("For development");
        material1.setEmployeeId(employeeId);

        Material material2 = new Material();
        material2.setItemName("Mouse");
        material2.setIssueDate("2024-01-05");
        material2.setNotes("For development");
        material2.setEmployeeId(employeeId);

        List<Material> materials = Arrays.asList(material1, material2);

        when(materialRepository.findByEmployeeId(employeeId)).thenReturn(materials);

        // Act
        List<MaterialDTO> materialDTOs = employeeService.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(2, materialDTOs.size());
        assertEquals("Laptop", materialDTOs.get(0).getItemName());
        assertEquals("Mouse", materialDTOs.get(1).getItemName());
        assertEquals(employeeId, materialDTOs.get(0).getEmployeeId());
        assertEquals(employeeId, materialDTOs.get(1).getEmployeeId());
    }

    @Test
    public void testGetMaterialsByEmployeeId_noMaterials() {
        // Arrange
        String employeeId = "456";
        when(materialRepository.findByEmployeeId(employeeId)).thenReturn(Arrays.asList());

        // Act
        List<MaterialDTO> materialDTOs = employeeService.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(0, materialDTOs.size());
    }
}