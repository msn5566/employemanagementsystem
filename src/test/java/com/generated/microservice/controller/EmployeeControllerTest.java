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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchEmployeesByPhoto_returnsOkAndEmployeeList() {
        // Arrange
        MultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        List<Employee> expectedEmployees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId("1");
        expectedEmployees.add(employee1);

        when(employeeService.findEmployeesByPhoto(photo)).thenReturn(expectedEmployees);

        // Act
        ResponseEntity<List<Employee>> responseEntity = employeeController.searchEmployeesByPhoto(photo);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEmployees, responseEntity.getBody());
    }

    @Test
    void searchEmployeesByPhoto_returnsOkAndEmptyList() {
        // Arrange
        MultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        List<Employee> expectedEmployees = new ArrayList<>();

        when(employeeService.findEmployeesByPhoto(photo)).thenReturn(expectedEmployees);

        // Act
        ResponseEntity<List<Employee>> responseEntity = employeeController.searchEmployeesByPhoto(photo);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEmployees, responseEntity.getBody());
    }
}