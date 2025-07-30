package com.generated.microservice.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsListOfMaterials() throws Exception {
        // Arrange
        String employeeId = "123";
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId("material1");
        materialDTO.setName("Laptop");
        materialDTO.setQuantity(1);
        materialDTO.setIssueDate(LocalDate.now());
        materialDTO.setEmployeeId(employeeId);

        List<MaterialDTO> materials = Collections.singletonList(materialDTO);
        when(employeeService.getMaterialsByEmployeeId(employeeId)).thenReturn(materials);

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("material1"))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].quantity").value(1))
                .andExpect(jsonPath("$[0].employeeId").value(employeeId));
    }

    @Test
    void getMaterialsByEmployeeId_ReturnsEmptyList() throws Exception {
        // Arrange
        String employeeId = "456";
        when(employeeService.getMaterialsByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}