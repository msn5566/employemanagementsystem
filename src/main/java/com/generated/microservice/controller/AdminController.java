package com.generated.microservice.controller;

import com.generated.microservice.dto.ExternalEmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import com.generated.microservice.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ExternalApiService externalApiService;
    private final EmployeeService employeeService;

    @PostMapping("/sync-employees")
    @PreAuthorize("hasRole('ADMIN')")
    public String syncEmployees(@RegisteredOAuth2AuthorizedClient("external-api-client") OAuth2AuthorizedClient authorizedClient) {
        List<ExternalEmployeeDTO> externalEmployeeData = externalApiService.fetchExternalEmployeeData(authorizedClient);

        List<Employee> employees = externalEmployeeData.stream()
                .map(externalEmployeeDTO -> {
                    Employee employee = new Employee();
                    employee.setEmployeeId(externalEmployeeDTO.getId());
                    employee.setName(externalEmployeeDTO.getName());
                    employee.setContactInformation(externalEmployeeDTO.getContactInformation());
                    return employee;
                })
                .collect(Collectors.toList());

        employeeService.saveEmployees(employees);

        return "Employees synchronized successfully.";
    }
}