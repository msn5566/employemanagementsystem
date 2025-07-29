package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExternalApiServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private OAuth2RestTemplate oAuth2RestTemplate;

    @InjectMocks
    private ExternalApiServiceImpl externalApiService;

    private final String EXTERNAL_API_URL = "https://external-api.com/data";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchAndSaveData_shouldFetchAndSaveEmployees() {
        // Arrange
        Employee[] employees = {
                new Employee(),
                new Employee()
        };
        when(oAuth2RestTemplate.getForObject(EXTERNAL_API_URL, Employee[].class)).thenReturn(employees);

        // Act
        externalApiService.fetchAndSaveData();

        // Assert
        verify(oAuth2RestTemplate, times(1)).getForObject(EXTERNAL_API_URL, Employee[].class);
        verify(employeeRepository, times(1)).saveAll(List.of(employees));
    }

    @Test
    void fetchAndSaveData_shouldNotSaveDataWhenApiReturnsNull() {
        // Arrange
        when(oAuth2RestTemplate.getForObject(EXTERNAL_API_URL, Employee[].class)).thenReturn(null);

        // Act
        externalApiService.fetchAndSaveData();

        // Assert
        verify(oAuth2RestTemplate, times(1)).getForObject(EXTERNAL_API_URL, Employee[].class);
        verify(employeeRepository, never()).saveAll(anyList());
    }
}