package com.generated.microservice.controller;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private MaterialController materialController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnOkAndListOfMaterialDTOs() {
        // Arrange
        String employeeId = "123";
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setMaterialName("Laptop");
        materialDTO.setEmployeeId(employeeId);
        materialDTO.setIssueDate(LocalDate.now());

        List<MaterialDTO> materialDTOs = Collections.singletonList(materialDTO);
        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(materialDTOs);

        // Act
        ResponseEntity<List<MaterialDTO>> response = materialController.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Laptop", response.getBody().get(0).getMaterialName());
        assertEquals(employeeId, response.getBody().get(0).getEmployeeId());
    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnOkAndEmptyListWhenNoMaterialsFound() {
        // Arrange
        String employeeId = "123";
        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<MaterialDTO>> response = materialController.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }
}