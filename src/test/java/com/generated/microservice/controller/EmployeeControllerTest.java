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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addEmployee_shouldCreateNewEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setContactInformation("john.doe@example.com");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.contactInformation").value("john.doe@example.com"));

        verify(employeeService, times(1)).addEmployee(any(Employee.class));
    }

    @Test
    void getEmployeeById_shouldReturnEmployee_whenEmployeeExists() throws Exception {
        String employeeId = "1";
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName("John Doe");
        employee.setContactInformation("john.doe@example.com");

        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.contactInformation").value("john.doe@example.com"));

        verify(employeeService, times(1)).getEmployeeById(employeeId);
    }

    @Test
    void getEmployeeById_shouldReturnNotFound_whenEmployeeDoesNotExist() throws Exception {
        String employeeId = "3";

        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).getEmployeeById(employeeId);
    }

    @Test
    void getAllEmployees_shouldReturnListOfEmployees() throws Exception {
        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");

        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setName("Jane Smith");
        employee2.setContactInformation("jane.smith@example.com");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void updateEmployee_shouldUpdateExistingEmployee() throws Exception {
        String employeeId = "1";
        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setName("John Doe");
        existingEmployee.setContactInformation("john.doe@example.com");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Updated Name");
        updatedEmployee.setContactInformation("updated.email@example.com");

        when(employeeService.updateEmployee(eq(employeeId), any(Employee.class))).thenReturn(existingEmployee);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.name").value("John Doe")) // Expecting existing employee
                .andExpect(jsonPath("$.contactInformation").value("john.doe@example.com")); // Expecting existing employee

        verify(employeeService, times(1)).updateEmployee(eq(employeeId), any(Employee.class));
    }

    @Test
    void updateEmployee_shouldReturnNotFound_whenEmployeeDoesNotExist() throws Exception {
        String employeeId = "3";
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Updated Name");
        updatedEmployee.setContactInformation("updated.email@example.com");

        when(employeeService.updateEmployee(eq(employeeId), any(Employee.class))).thenThrow(new IllegalArgumentException("Employee not found"));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).updateEmployee(eq(employeeId), any(Employee.class));
    }

    @Test
    void deleteEmployee_shouldDeleteExistingEmployee() throws Exception {
        String employeeId = "1";
        doNothing().when(employeeService).deleteEmployee(employeeId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }

    @Test
    void searchEmployeesByName_shouldReturnOkAndMatchingEmployees() throws Exception {
        // Arrange
        String name = "test";
        Employee employee1 = new Employee();
        employee1.setName("test1");
        Employee employee2 = new Employee();
        employee2.setName("test2");
        List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);

        when(employeeService.findEmployeesByName(name)).thenReturn(expectedEmployees);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/search?name=" + name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}