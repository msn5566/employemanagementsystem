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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DataSyncServiceImplTest {

    @Mock
    private ExternalApiService externalApiService;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DataSyncServiceImpl dataSyncService;

    @Test
    void syncEmployeeData_shouldFetchAndSaveEmployeeData() {
        // Arrange
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setName("John Doe");
        employeeDTO1.setContactInformation("john.doe@example.com");
        employeeDTOs.add(employeeDTO1);
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setName("Jane Smith");
        employeeDTO2.setContactInformation("jane.smith@example.com");
        employeeDTOs.add(employeeDTO2);

        when(externalApiService.fetchEmployeeData()).thenReturn(employeeDTOs);

        // Act
        dataSyncService.syncEmployeeData();

        // Assert
        verify(externalApiService, times(1)).fetchEmployeeData();
        verify(employeeRepository, times(1)).saveAll(org.mockito.ArgumentMatchers.anyList());
    }
}