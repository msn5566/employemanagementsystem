package com.generated.microservice.service;

import com.generated.microservice.dto.ExternalEmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {

    @Value("${external.api.url}")
    private String externalApiUrl;

    private final WebClient webClient;

    @Override
    public List<ExternalEmployeeDTO> fetchExternalEmployeeData(OAuth2AuthorizedClient authorizedClient) {
        return webClient.method(HttpMethod.GET)
                .uri(externalApiUrl)
                .headers(headers -> {
                    headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue());
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ExternalEmployeeDTO>>() {})
                .block();
    }
}