package com.generated.microservice.service;

import com.generated.microservice.dto.ExternalEmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {

    private final OAuth2AuthorizedClientService authorizedClientService;

    private final WebClient webClient;

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Value("${external.api.client.registration.id}")
    private String clientRegistrationId;

    @Override
    public List<ExternalEmployeeDTO> fetchEmployeeData() {
        OAuth2AuthenticationToken authentication =
                (OAuth2AuthenticationToken) org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        OAuth2AuthorizedClient authorizedClient =
                authorizedClientService.loadAuthorizedClient(
                        clientRegistrationId, authentication.getName());

        if (authorizedClient == null) {
            return new ArrayList<>();
        }

        return webClient
                .get()
                .uri(externalApiUrl)
                .attributes(
                        ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    // Handle error responses
                    return Mono.error(
                            new RuntimeException("Error fetching data from external API: " + clientResponse.statusCode()));
                })
                .bodyToMono(new ParameterizedTypeReference<List<ExternalEmployeeDTO>>() {})
                .block();
    }
}