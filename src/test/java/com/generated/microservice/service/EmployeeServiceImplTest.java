package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.ExternalEmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(employeeService, "externalApiUrl", "https://example.com/api/employees");
    }

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
    void fetchDataAndSave_shouldFetchDataAndSaveToDatabase() {
        // Arrange
        ExternalEmployeeDTO externalEmployeeDTO1 = new ExternalEmployeeDTO();
        externalEmployeeDTO1.setId("1");
        externalEmployeeDTO1.setName("John Doe");
        externalEmployeeDTO1.setContactInformation("john.doe@example.com");

        ExternalEmployeeDTO externalEmployeeDTO2 = new ExternalEmployeeDTO();
        externalEmployeeDTO2.setId("2");
        externalEmployeeDTO2.setName("Jane Smith");
        externalEmployeeDTO2.setContactInformation("jane.smith@example.com");

        List<ExternalEmployeeDTO> externalEmployeeDTOs = List.of(externalEmployeeDTO1, externalEmployeeDTO2);

        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri("https://example.com/api/employees")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(ExternalEmployeeDTO.class)).thenReturn(Flux.fromIterable(externalEmployeeDTOs));

        // Act
        employeeService.fetchDataAndSave();

        // Assert
        verify(employeeRepository, times(1)).saveAll(Mockito.anyList());
    }

    @Test
    void fetchDataAndSave_shouldNotSaveDataWhenExternalApiReturnsNull() {
        // Arrange
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri("https://example.com/api/employees")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(ExternalEmployeeDTO.class)).thenReturn(Flux.empty());


        // Act
        employeeService.fetchDataAndSave();

        // Assert
        verify(employeeRepository, never()).saveAll(any());
    }
}