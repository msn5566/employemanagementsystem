package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalDataServiceImpl implements ExternalDataService {

    private final EmployeeRepository employeeRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Override
    public void synchronizeData() {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        if (authorizedClient != null) {
            WebClient webClient = WebClient.builder()
                    .baseUrl(externalApiUrl)
                    .defaultHeader("Authorization", "Bearer " + authorizedClient.getAccessToken().getTokenValue())
                    .build();

            Flux<Employee> employeeFlux = webClient.get()
                    .uri("/employees") // Assuming the API endpoint is /employees
                    .retrieve()
                    .bodyToFlux(Employee.class);

            List<Employee> employees = employeeFlux.collectList().block();

            if (employees != null && !employees.isEmpty()) {
                employeeRepository.saveAll(employees);
            }
        } else {
            System.err.println("OAuth2 Authorized Client not found!");
        }
    }
}