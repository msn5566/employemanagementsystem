package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeControllerTest {

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
    void findEmployeesByPhoto_shouldReturnListOfEmployees() throws Exception {
        // Arrange
        String photo = "test_photo.jpg";
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setPhoto(photo);
        List<Employee> expectedEmployees = Collections.singletonList(employee);

        Mockito.when(employeeService.findEmployeesByPhoto(photo)).thenReturn(expectedEmployees);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/photo")
                        .param("photo", photo))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findEmployeesByPhoto_shouldReturnEmptyList_whenNoEmployeesFound() throws Exception {
        // Arrange
        String photo = "nonexistent_photo.jpg";
        Mockito.when(employeeService.findEmployeesByPhoto(photo)).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/photo")
                        .param("photo", photo))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}