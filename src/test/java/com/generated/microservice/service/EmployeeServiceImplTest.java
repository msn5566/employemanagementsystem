package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void addEmployee_ValidInput_ReturnsEmployeeId() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setContactInformation("john.doe@example.com");

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        when(employeeRepository.save(employeeCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        String employeeId = employeeService.addEmployee(employeeDTO);

        // Assert
        assertNotNull(employeeId);
        verify(employeeRepository, times(1)).save(employeeCaptor.capture());
        Employee savedEmployee = employeeCaptor.getValue();

        assertEquals("John Doe", savedEmployee.getName());
        assertEquals("john.doe@example.com", savedEmployee.getContactInformation());
        assertEquals(employeeId, savedEmployee.getEmployeeId());
    }

    @Test
    void findEmployeesByPhoto_ReturnsListOfEmployeeDTOs() {
        // Arrange
        MultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test data".getBytes());
        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setEmployeeId("E1");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");

        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setEmployeeId("E2");
        employee2.setName("Jane Smith");
        employee2.setContactInformation("jane.smith@example.com");

        List<Employee> employeeList = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Act
        List<EmployeeDTO> employeeDTOList = employeeService.findEmployeesByPhoto(photo);

        // Assert
        assertEquals(2, employeeDTOList.size());

        assertEquals("1", employeeDTOList.get(0).getId());
        assertEquals("E1", employeeDTOList.get(0).getEmployeeId());
        assertEquals("John Doe", employeeDTOList.get(0).getName());
        assertEquals("john.doe@example.com", employeeDTOList.get(0).getContactInformation());

        assertEquals("2", employeeDTOList.get(1).getId());
        assertEquals("E2", employeeDTOList.get(1).getEmployeeId());
        assertEquals("Jane Smith", employeeDTOList.get(1).getName());
        assertEquals("jane.smith@example.com", employeeDTOList.get(1).getContactInformation());
    }
}