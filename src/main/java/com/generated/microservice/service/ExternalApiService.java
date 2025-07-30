package com.generated.microservice.service;

import com.generated.microservice.dto.ExternalEmployeeDTO;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import java.util.List;

public interface ExternalApiService {

    List<ExternalEmployeeDTO> fetchExternalEmployeeData(OAuth2AuthorizedClient authorizedClient);
}