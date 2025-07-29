package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {

    private final EmployeeRepository employeeRepository;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    private static final String EXTERNAL_API_URL = "https://external-api.com/data"; // Replace with actual API URL

    @Override
    public void fetchAndSaveData() {
        // Fetch data from the external API
        Employee[] employees = oAuth2RestTemplate.getForObject(EXTERNAL_API_URL, Employee[].class);

        // Save the fetched data to the database
        if (employees != null) {
            employeeRepository.saveAll(Arrays.asList(employees));
        }
    }
}