package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee("1", "John Doe", "IT", "Developer");
        employee2 = new Employee("2", "Jane Doe", "HR", "Manager");
    }

    @Test
    void searchEmployeesByName_shouldReturnOk_andMatchingEmployees() throws Exception {
        // Arrange
        String searchName = "Doe";
        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeService.searchEmployeesByName(searchName)).thenReturn(employees);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/search")
                        .param("name", searchName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    void searchEmployeesByName_shouldReturnOk_andEmptyList_whenNoMatchingEmployeesFound() throws Exception {
        // Arrange
        String searchName = "Smith";
        when(employeeService.searchEmployeesByName(searchName)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/search")
                        .param("name", searchName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}