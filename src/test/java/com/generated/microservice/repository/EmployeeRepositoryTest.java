package com.generated.microservice.repository;

import com.generated.microservice.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void findByPhoto_shouldReturnListOfEmployees() {
        // Arrange
        String photo = "test_photo.jpg";
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setPhoto(photo);
        List<Employee> expectedEmployees = Collections.singletonList(employee);

        Mockito.when(employeeRepository.findByPhoto(photo)).thenReturn(expectedEmployees);

        // Act
        List<Employee> actualEmployees = employeeRepository.findByPhoto(photo);

        // Assert
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void findByPhoto_shouldReturnEmptyList_whenNoEmployeesFound() {
        // Arrange
        String photo = "nonexistent_photo.jpg";
        Mockito.when(employeeRepository.findByPhoto(photo)).thenReturn(Collections.emptyList());

        // Act
        List<Employee> actualEmployees = employeeRepository.findByPhoto(photo);

        // Assert
        assertEquals(Collections.emptyList(), actualEmployees);
    }
}