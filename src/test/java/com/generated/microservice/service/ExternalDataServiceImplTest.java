package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExternalDataServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private OAuth2AuthorizedClientService authorizedClientService;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private ExternalDataServiceImpl externalDataService;

    @Test
    public void synchronizeData_shouldSaveEmployeesToDatabase() {
        // Arrange
        String accessTokenValue = "test_access_token";
        String clientRegistrationId = "test_client_registration_id";
        String principalName = "test_user";

        OAuth2AuthorizedClient authorizedClient = new OAuth2AuthorizedClient(
                clientRegistrationId,
                principalName,
                org.springframework.security.oauth2.core.OAuth2AccessToken.withToken(accessTokenValue)
                        .tokenType(org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType.BEARER)
                        .issuedAt(Instant.now())
                        .expiresAt(Instant.now().plusSeconds(3600))
                        .build()
        );

        OAuth2AuthenticationToken authentication = mock(OAuth2AuthenticationToken.class);
        when(authentication.getAuthorizedClientRegistrationId()).thenReturn(clientRegistrationId);
        when(authentication.getName()).thenReturn(principalName);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);


        when(authorizedClientService.loadAuthorizedClient(clientRegistrationId, principalName)).thenReturn(authorizedClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        Flux<Employee> employeeFlux = Flux.just(employee);

        when(responseSpec.bodyToFlux(Employee.class)).thenReturn(employeeFlux);
        when(employeeRepository.saveAll(anyList())).thenReturn(Collections.singletonList(employee));

        // Act
        externalDataService.synchronizeData();

        // Assert
        verify(employeeRepository, times(1)).saveAll(anyList());
        SecurityContextHolder.clearContext();
    }

    @Test
    public void synchronizeData_shouldNotSaveEmployeesWhenNoAuthorizedClientFound() {
        // Arrange
        String clientRegistrationId = "test_client_registration_id";
        String principalName = "test_user";

        OAuth2AuthenticationToken authentication = mock(OAuth2AuthenticationToken.class);
        when(authentication.getAuthorizedClientRegistrationId()).thenReturn(clientRegistrationId);
        when(authentication.getName()).thenReturn(principalName);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authorizedClientService.loadAuthorizedClient(clientRegistrationId, principalName)).thenReturn(null);

        // Act
        externalDataService.synchronizeData();

        // Assert
        verify(employeeRepository, never()).saveAll(anyList());
        SecurityContextHolder.clearContext();
    }
}