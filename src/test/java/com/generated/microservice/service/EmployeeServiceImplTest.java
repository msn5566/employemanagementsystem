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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletOutputStream outputStream;

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
    public void testExportEmployeesToExcel() throws IOException {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");
        employees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeId("2");
        employee2.setName("Jane Smith");
        employee2.setContactInformation("jane.smith@example.com");
        employees.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employees);
        when(response.getOutputStream()).thenReturn(outputStream);
        doNothing().when(outputStream).close();

        // Act
        employeeService.exportEmployeesToExcel(response);

        // Assert
        verify(employeeRepository, times(1)).findAll();
        verify(response, times(1)).getOutputStream();
        verify(outputStream, times(1)).close(); // Verify that the output stream is closed
    }
}