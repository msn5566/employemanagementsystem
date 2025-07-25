package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
    public void testSearchEmployeesByPhoto() throws IOException {
        // Arrange
        MultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        List<Employee> expectedEmployees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("John Doe");
        expectedEmployees.add(employee1);

        when(employeeService.findEmployeesByPhoto("test.jpg")).thenReturn(expectedEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.searchEmployeesByPhoto(photo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployees, response.getBody());
    }
}