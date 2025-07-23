package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getEmployeesByName_shouldReturnEmployees() throws Exception {
        Employee employee1 = new Employee("1", "John", "Doe", "john.doe@example.com", "IT");
        Employee employee2 = new Employee("2", "John", "Smith", "john.smith@example.com", "HR");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.findEmployeesByName("John")).thenReturn(employees);

        mockMvc.perform(get("/api/employees/search?name=John")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("John"));
    }

    @Test
    void getEmployeesByName_shouldReturnNotFound() throws Exception {
        when(employeeService.findEmployeesByName("NonExistent")).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/employees/search?name=NonExistent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createEmployee_shouldReturnCreatedEmployee() throws Exception {
        Employee employee = new Employee("1", "John", "Doe", "john.doe@example.com", "IT");
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void getEmployeeById_shouldReturnEmployee() throws Exception {
        Employee employee = new Employee("1", "John", "Doe", "john.doe@example.com", "IT");
        when(employeeService.getEmployeeById("1")).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void getEmployeeById_shouldReturnNotFound() throws Exception {
        when(employeeService.getEmployeeById("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateEmployee_shouldReturnUpdatedEmployee() throws Exception {
        Employee employeeDetails = new Employee("1", "UpdatedJohn", "Doe", "john.doe@example.com", "IT");
        Employee updatedEmployee = new Employee("1", "UpdatedJohn", "Doe", "john.doe@example.com", "IT");
        when(employeeService.updateEmployee(eq("1"), any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("UpdatedJohn"));
    }

    @Test
    void updateEmployee_shouldReturnNotFound() throws Exception {
        Employee employeeDetails = new Employee("1", "UpdatedJohn", "Doe", "john.doe@example.com", "IT");
        when(employeeService.updateEmployee(eq("1"), any(Employee.class))).thenThrow(new RuntimeException("Employee not found"));

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDetails)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEmployee_shouldReturnNoContent() throws Exception {
        doNothing().when(employeeService).deleteEmployee("1");

        mockMvc.perform(delete("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).deleteEmployee("1");
    }
}