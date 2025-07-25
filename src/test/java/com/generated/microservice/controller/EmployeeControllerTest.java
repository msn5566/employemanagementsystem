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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void searchEmployeesByPhoto_returnsOkStatusAndEmployeeList() {
        // Arrange
        MultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee());
        when(employeeService.findEmployeesByPhoto(photo)).thenReturn(expectedEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.searchEmployeesByPhoto(photo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployees, response.getBody());
    }

    @Test
    void searchEmployeesByPhoto_returnsEmptyList() {
        // Arrange
        MultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        List<Employee> expectedEmployees = new ArrayList<>();
        when(employeeService.findEmployeesByPhoto(photo)).thenReturn(expectedEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.searchEmployeesByPhoto(photo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployees, response.getBody());
    }
}