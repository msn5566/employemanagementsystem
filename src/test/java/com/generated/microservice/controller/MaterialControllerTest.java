package com.generated.microservice.controller;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnOkAndListOfMaterials() throws Exception {
        String employeeId = "123";
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId("material1");
        materialDTO.setMaterialName("Laptop");
        materialDTO.setIssueDate(new Date());
        materialDTO.setDescription("Dell XPS 15");
        List<MaterialDTO> materials = Collections.singletonList(materialDTO);

        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(materials);

        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("material1"))
                .andExpect(jsonPath("$[0].materialName").value("Laptop"));

        verify(materialService, times(1)).getMaterialsByEmployeeId(employeeId);
    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnOkAndEmptyListWhenNoMaterialsFound() throws Exception {
        String employeeId = "456";
        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(materialService, times(1)).getMaterialsByEmployeeId(employeeId);
    }
}