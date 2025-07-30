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
    private MaterialRepository materialRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getMaterialsByEmployeeId_ReturnsListOfMaterialDTOs() {
        // Arrange
        String employeeId = "123";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        Material material = new Material();
        material.setId("material1");
        material.setName("Laptop");
        material.setQuantity(1);
        material.setIssueDate(LocalDate.now());
        material.setEmployee(employee);

        List<Material> materials = Collections.singletonList(material);
        when(materialRepository.findByEmployee_EmployeeId(employeeId)).thenReturn(materials);

        // Act
        List<MaterialDTO> materialDTOs = employeeService.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(1, materialDTOs.size());
        assertEquals("material1", materialDTOs.get(0).getId());
        assertEquals("Laptop", materialDTOs.get(0).getName());
        assertEquals(1, materialDTOs.get(0).getQuantity());
        assertEquals(employeeId, materialDTOs.get(0).getEmployeeId());
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsEmptyList() {
        // Arrange
        String employeeId = "456";
        when(materialRepository.findByEmployee_EmployeeId(employeeId)).thenReturn(Collections.emptyList());

        // Act
        List<MaterialDTO> materialDTOs = employeeService.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(0, materialDTOs.size());
    }

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
}