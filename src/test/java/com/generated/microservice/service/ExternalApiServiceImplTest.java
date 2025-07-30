package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExternalApiServiceImplTest {

    @Mock
    private OAuth2AuthorizedClientManager authorizedClientManager;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private ExternalApiServiceImpl externalApiService;

    @BeforeEach
    void setUp() {
        externalApiService = new ExternalApiServiceImpl(authorizedClientManager);
        externalApiService.setApiUrl("http://test.example.com");
    }

    @Test
    void fetchEmployeeData_shouldReturnListOfEmployeeDTOs() {
        OAuth2AuthorizedClient authorizedClient = mock(OAuth2AuthorizedClient.class);
        OAuth2AccessToken accessToken = new OAuth2AccessToken(TokenType.BEARER, "testToken", Instant.now(), Instant.now().plusSeconds(3600));
        when(authorizedClient.getAccessToken()).thenReturn(accessToken);
        when(authorizedClientManager.authorize(any())).thenReturn(authorizedClient);

        List<EmployeeDTO> mockEmployeeDTOs = List.of(new EmployeeDTO(), new EmployeeDTO());

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/employees")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(EmployeeDTO.class)).thenReturn(Flux.fromIterable(mockEmployeeDTOs));
        when(webClient.mutate()).thenReturn(WebClient.builder());


        ExternalApiServiceImpl externalApiServiceSpy = new ExternalApiServiceImpl(authorizedClientManager);
        externalApiServiceSpy.setApiUrl("http://test.example.com");

        externalApiServiceSpy.setWebClient(webClient);

        List<EmployeeDTO> employeeDTOs = externalApiServiceSpy.fetchEmployeeData();

        assertEquals(2, employeeDTOs.size());
    }

    @Test
    void fetchEmployeeData_shouldReturnEmptyList_whenNoAuthorizedClient() {
        when(authorizedClientManager.authorize(any())).thenReturn(null);

        List<EmployeeDTO> employeeDTOs = externalApiService.fetchEmployeeData();

        assertTrue(employeeDTOs.isEmpty());
    }
}