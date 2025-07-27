package com.generated.microservice.controller;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.verify;

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
    void searchEmployeesByPhoto_ReturnsOk() throws Exception {
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image".getBytes());
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());

        when(employeeService.findEmployeesByPhoto(any())).thenReturn(employees);

        mockMvc.perform(multipart("/employees/searchByPhoto")
                        .file(photo))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(employeeService).findEmployeesByPhoto(any());
    }

    @Test
    void searchEmployeesByPhoto_ReturnsNoContent() throws Exception {
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image".getBytes());
        List<Employee> employees = new ArrayList<>();

        when(employeeService.findEmployeesByPhoto(any())).thenReturn(employees);

        mockMvc.perform(multipart("/employees/searchByPhoto")
                        .file(photo))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(employeeService).findEmployeesByPhoto(any());
    }
}