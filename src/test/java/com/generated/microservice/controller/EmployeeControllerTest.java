package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void searchEmployeesByPhoto_returnsOk() throws Exception {
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());

        when(employeeService.findEmployeesByPhoto(any())).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/employees/searchByPhoto")
                        .file(photo)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists());
    }

    @Test
    void searchEmployeesByPhoto_noEmployeesFound() throws Exception {
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());
        List<Employee> employees = new ArrayList<>();

        when(employeeService.findEmployeesByPhoto(any())).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/employees/searchByPhoto")
                        .file(photo)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}