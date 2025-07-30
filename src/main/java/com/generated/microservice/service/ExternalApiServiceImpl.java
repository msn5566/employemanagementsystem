package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Value("${external.api.url}")
    private String apiUrl;

    private WebClient webClient;

    @Autowired
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<EmployeeDTO> fetchEmployeeData() {
        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(org.springframework.security.oauth2.client.OAuth2AuthorizeRequest.withClientRegistrationId("external").principal("employee-service").build());

        if (authorizedClient != null) {
            String accessToken = authorizedClient.getAccessToken().getTokenValue();

            if (webClient == null) {
                webClient = WebClient.builder()
                        .baseUrl(apiUrl)
                        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();
            }
            return webClient.get()
                    .uri("/employees")
                    .retrieve()
                    .bodyToFlux(EmployeeDTO.class)
                    .collectList()
                    .block();
        }
        return List.of();
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}