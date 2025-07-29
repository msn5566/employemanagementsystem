package com.generated.microservice.controller;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private MaterialController materialController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(materialController).build();
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsListOfMaterials() {
        // Arrange
        String employeeId = "123";
        MaterialDTO material1 = new MaterialDTO();
        material1.setId("1");
        material1.setEmployeeId(employeeId);
        material1.setName("Laptop");
        material1.setIssueDate(LocalDate.of(2025, 7, 30));
        material1.setDescription("Dell XPS 15");

        MaterialDTO material2 = new MaterialDTO();
        material2.setId("2");
        material2.setEmployeeId(employeeId);
        material2.setName("Monitor");
        material2.setIssueDate(LocalDate.of(2025, 7, 30));
        material2.setDescription("Dell 27 inch");

        List<MaterialDTO> materials = Arrays.asList(material1, material2);

        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(materials);

        // Act
        ResponseEntity<List<MaterialDTO>> response = materialController.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materials, response.getBody());
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsEmptyList() {
        // Arrange
        String employeeId = "456";
        List<MaterialDTO> materials = Arrays.asList();

        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(materials);

        // Act
        ResponseEntity<List<MaterialDTO>> response = materialController.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materials, response.getBody());
    }
}