package com.generated.microservice.controller;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void addEmployee_ValidInput_ReturnsCreated() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setContactInformation("john.doe@example.com");
        String employeeId = "123e4567-e89b-12d3-a456-426614174000";
        when(employeeService.addEmployee(any(EmployeeDTO.class))).thenReturn(employeeId);

        // Act
        ResponseEntity<String> responseEntity = employeeController.addEmployee(employeeDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Employee added successfully with ID: " + employeeId, responseEntity.getBody());
    }

    @Test
    public void testGetMaterialsByEmployeeId() throws Exception {
        // Arrange
        String employeeId = "123";
        MaterialDTO materialDTO1 = new MaterialDTO();
        materialDTO1.setItemName("Laptop");
        materialDTO1.setIssueDate("2024-01-01");
        materialDTO1.setNotes("For development");
        materialDTO1.setEmployeeId(employeeId);

        MaterialDTO materialDTO2 = new MaterialDTO();
        materialDTO2.setItemName("Mouse");
        materialDTO2.setIssueDate("2024-01-05");
        materialDTO2.setNotes("For development");
        materialDTO2.setEmployeeId(employeeId);

        List<MaterialDTO> materialDTOs = Arrays.asList(materialDTO1, materialDTO2);

        when(employeeService.getMaterialsByEmployeeId(employeeId)).thenReturn(materialDTOs);

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].itemName").value("Laptop"))
                .andExpect(jsonPath("$[1].itemName").value("Mouse"))
                .andExpect(jsonPath("$[0].employeeId").value(employeeId))
                .andExpect(jsonPath("$[1].employeeId").value(employeeId));
    }

    @Test
    public void testGetMaterialsByEmployeeId_noMaterials() throws Exception {
        // Arrange
        String employeeId = "456";
        when(employeeService.getMaterialsByEmployeeId(employeeId)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}