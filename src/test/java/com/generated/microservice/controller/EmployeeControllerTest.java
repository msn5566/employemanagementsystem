package com.generated.microservice.controller;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    void searchEmployeesByPhoto_ReturnsListOfEmployees() throws Exception {
        // Arrange
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        EmployeeDTO employee1 = new EmployeeDTO();
        employee1.setId("1");
        employee1.setEmployeeId("E1");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");

        EmployeeDTO employee2 = new EmployeeDTO();
        employee2.setId("2");
        employee2.setEmployeeId("E2");
        employee2.setName("Jane Smith");
        employee2.setContactInformation("jane.smith@example.com");

        List<EmployeeDTO> employeeList = Arrays.asList(employee1, employee2);

        when(employeeService.findEmployeesByPhoto(any())).thenReturn(employeeList);

        // Act & Assert
        mockMvc.perform(multipart("/employees/searchByPhoto")
                        .file(photo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].employeeId").value("E1"))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].contactInformation").value("john.doe@example.com"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].employeeId").value("E2"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].contactInformation").value("jane.smith@example.com"));
    }
}