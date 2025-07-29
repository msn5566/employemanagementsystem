package com.generated.microservice.controller;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetMaterialsByEmployeeId() throws Exception {
        // Arrange
        String employeeId = "123";
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId("material1");
        materialDTO.setEmployeeId(employeeId);
        materialDTO.setName("Laptop");
        materialDTO.setIssueDate(LocalDate.now());
        materialDTO.setDescription("Dell XPS 15");

        List<MaterialDTO> materials = Collections.singletonList(materialDTO);
        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(materials);

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("material1"))
                .andExpect(jsonPath("$[0].employeeId").value(employeeId))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].description").value("Dell XPS 15"));
    }

    @Test
    public void testGetMaterialsByEmployeeId_noMaterialsFound() throws Exception {
        // Arrange
        String employeeId = "456";
        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}