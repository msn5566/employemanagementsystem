package com.generated.microservice.util;

import com.generated.microservice.entity.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExcelExporterTest {

    @Test
    void employeesToExcel_shouldReturnByteArrayInputStream_whenEmployeesProvided() throws IOException {
        // Arrange
        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setEmployeeId("E001");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");

        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setEmployeeId("E002");
        employee2.setName("Jane Smith");
        employee2.setContactInformation("jane.smith@example.com");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        // Act
        ByteArrayInputStream inputStream = ExcelExporter.employeesToExcel(employees);

        // Assert
        assertNotNull(inputStream);
        assertTrue(inputStream.available() > 0);
    }

    @Test
    void employeesToExcel_shouldHandleNullEmployeeId() throws IOException {
        // Arrange
        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");

        List<Employee> employees = Arrays.asList(employee1);

        // Act
        ByteArrayInputStream inputStream = ExcelExporter.employeesToExcel(employees);

        // Assert
        assertNotNull(inputStream);
        assertTrue(inputStream.available() > 0);
    }
}