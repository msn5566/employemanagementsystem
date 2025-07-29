package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.ExternalEmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

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
    public void syncEmployeesFromExternalApi(String apiUrl) {
        WebClient webClient = WebClient.create(apiUrl);

        Flux<ExternalEmployeeDTO> employeeFlux = webClient.get()
            .retrieve()
            .bodyToFlux(ExternalEmployeeDTO.class);

        List<Employee> employees = employeeFlux.map(this::convertToEmployee)
            .collectList()
            .block();

        if (employees != null) {
            employeeRepository.saveAll(employees);
        }
    }

    private Employee convertToEmployee(ExternalEmployeeDTO externalEmployeeDTO) {
        Employee employee = new Employee();
        employee.setName(externalEmployeeDTO.getName());
        employee.setContactInformation(externalEmployeeDTO.getContactInformation());
        return employee;
    }
}