package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialIssueDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.Material;
import com.generated.microservice.entity.MaterialIssue;
import com.generated.microservice.repository.MaterialIssueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialIssueServiceImplTest {

    @Mock
    private MaterialIssueRepository materialIssueRepository;

    @InjectMocks
    private MaterialIssueServiceImpl materialIssueService;

    private Employee employee;
    private Material material;
    private MaterialIssue materialIssue1;
    private MaterialIssue materialIssue2;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmployeeId("123");

        material = new Material();
        material.setId("456");
        material.setName("Laptop");

        materialIssue1 = new MaterialIssue();
        materialIssue1.setId("789");
        materialIssue1.setEmployee(employee);
        materialIssue1.setMaterial(material);
        materialIssue1.setIssueDate(new Date());
        materialIssue1.setQuantity(1);

        material = new Material();
        material.setId("987");
        material.setName("Mouse");

        materialIssue2 = new MaterialIssue();
        materialIssue2.setId("654");
        materialIssue2.setEmployee(employee);
        materialIssue2.setMaterial(material);
        materialIssue2.setIssueDate(new Date());
        materialIssue2.setQuantity(2);
    }

    @Test
    void getMaterialsIssuedByEmployeeId_ShouldReturnListOfMaterialIssueDTOs() {
        // Arrange
        String employeeId = "123";
        when(materialIssueRepository.findByEmployeeEmployeeId(employeeId)).thenReturn(Arrays.asList(materialIssue1, materialIssue2));

        // Act
        List<MaterialIssueDTO> materialIssueDTOs = materialIssueService.getMaterialsIssuedByEmployeeId(employeeId);

        // Assert
        assertEquals(2, materialIssueDTOs.size());
        assertEquals("Laptop", materialIssueDTOs.get(0).getMaterialName());
        assertEquals(1, materialIssueDTOs.get(0).getQuantity());
        assertEquals("Mouse", materialIssueDTOs.get(1).getMaterialName());
        assertEquals(2, materialIssueDTOs.get(1).getQuantity());
    }

    @Test
    void getMaterialsIssuedByEmployeeId_ShouldReturnEmptyList_WhenNoMaterialsIssued() {
        // Arrange
        String employeeId = "456";
        when(materialIssueRepository.findByEmployeeEmployeeId(employeeId)).thenReturn(java.util.Collections.emptyList());

        // Act
        List<MaterialIssueDTO> materialIssueDTOs = materialIssueService.getMaterialsIssuedByEmployeeId(employeeId);

        // Assert
        assertEquals(0, materialIssueDTOs.size());
    }
}