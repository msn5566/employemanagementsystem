package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.ExternalEmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient webClient;

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setContactInformation(employeeDTO.getContactInformation());
        String employeeId = UUID.randomUUID().toString();
        employee.setEmployeeId(employeeId);
        employeeRepository.save(employee);
        return employeeId;
    }

    @Override
    public void fetchDataAndSave() {
        List<ExternalEmployeeDTO> externalEmployeeDTOs = webClient.get()
                .uri(externalApiUrl)
                .retrieve()
                .bodyToFlux(ExternalEmployeeDTO.class)
                .collectList()
                .block();

        if (externalEmployeeDTOs != null && !externalEmployeeDTOs.isEmpty()) {
            List<Employee> employees = externalEmployeeDTOs.stream()
                    .map(this::convertToEmployee)
                    .collect(Collectors.toList());

            employeeRepository.saveAll(employees);
        }
    }

    private Employee convertToEmployee(ExternalEmployeeDTO externalEmployeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(externalEmployeeDTO.getId());
        employee.setName(externalEmployeeDTO.getName());
        employee.setContactInformation(externalEmployeeDTO.getContactInformation());
        return employee;
    }
}