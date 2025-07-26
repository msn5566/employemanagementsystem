package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testFindEmployeesByPhoto() {
        // Arrange
        Employee employee = new Employee();
        employee.setId("1");
        employee.setEmployeeId("E1");
        employee.setName("John Doe");
        employee.setContactInformation("john.doe@example.com");

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(employeeRepository.findAll()).thenReturn(employees);

        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());

        // Act
        List<EmployeeDTO> employeeDTOs = employeeService.findEmployeesByPhoto(photo);

        // Assert
        assertEquals(1, employeeDTOs.size());
        assertEquals("1", employeeDTOs.get(0).getId());
        assertEquals("E1", employeeDTOs.get(0).getEmployeeId());
        assertEquals("John Doe", employeeDTOs.get(0).getName());
        assertEquals("john.doe@example.com", employeeDTOs.get(0).getContactInformation());
    }
}