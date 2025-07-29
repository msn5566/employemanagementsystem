package com.generated.microservice.controller;

import com.generated.microservice.dto.MaterialIssueDTO;
import com.generated.microservice.service.MaterialIssueService;
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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialIssueControllerTest {

    @Mock
    private MaterialIssueService materialIssueService;

    @InjectMocks
    private EmployeeController employeeController; // Using EmployeeController since the endpoint is there

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getMaterialsIssuedToEmployee_ShouldReturnOkAndMaterials() throws Exception {
        // Arrange
        String employeeId = "123";
        MaterialIssueDTO materialIssueDTO = new MaterialIssueDTO();
        materialIssueDTO.setMaterialName("Laptop");
        materialIssueDTO.setIssueDate(new Date());
        materialIssueDTO.setQuantity(1);

        List<MaterialIssueDTO> materialIssueDTOs = Collections.singletonList(materialIssueDTO);
        when(materialIssueService.getMaterialsIssuedByEmployeeId(employeeId)).thenReturn(materialIssueDTOs);

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].materialName").value("Laptop"))
                .andExpect(jsonPath("$[0].quantity").value(1));
    }

    @Test
    void getMaterialsIssuedToEmployee_ShouldReturnOkAndEmptyList_WhenNoMaterialsIssued() throws Exception {
        // Arrange
        String employeeId = "456";
        when(materialIssueService.getMaterialsIssuedByEmployeeId(employeeId)).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/employees/{employeeId}/materials", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}