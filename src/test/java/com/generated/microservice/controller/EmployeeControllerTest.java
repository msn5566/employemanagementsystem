package com.generated.microservice.controller;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.EmployeeService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

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
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetEmployeeMaterials() throws Exception {
        // Mock the service response
        MaterialDTO material1 = new MaterialDTO();
        material1.setMaterialName("Laptop");
        material1.setIssueDate("2024-01-15");
        material1.setMetadata("Dell XPS 15");

        MaterialDTO material2 = new MaterialDTO();
        material2.setMaterialName("Office Chair");
        material2.setIssueDate("2023-12-01");
        material2.setMetadata("Ergonomic");

        List<MaterialDTO> materials = Arrays.asList(material1, material2);
        when(employeeService.getEmployeeMaterials("123")).thenReturn(materials);

        // Perform the request and assert the response
        mockMvc.perform(get("/employees/123/materials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].materialName").value("Laptop"))
                .andExpect(jsonPath("$[1].materialName").value("Office Chair"));
    }
}