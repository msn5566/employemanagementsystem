package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.entity.Material;
import com.generated.microservice.repository.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialServiceImplTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialServiceImpl materialService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnListOfMaterialDTOs() {
        // Arrange
        String employeeId = "123";
        Material material = new Material();
        material.setMaterialName("Laptop");
        material.setEmployeeId(employeeId);
        material.setIssueDate(LocalDate.now());

        List<Material> materials = Collections.singletonList(material);
        when(materialRepository.findByEmployeeId(employeeId)).thenReturn(materials);

        // Act
        List<MaterialDTO> materialDTOs = materialService.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(1, materialDTOs.size());
        assertEquals("Laptop", materialDTOs.get(0).getMaterialName());
        assertEquals(employeeId, materialDTOs.get(0).getEmployeeId());
    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnEmptyListWhenNoMaterialsFound() {
        // Arrange
        String employeeId = "123";
        when(materialRepository.findByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        // Act
        List<MaterialDTO> materialDTOs = materialService.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(0, materialDTOs.size());
    }
}