package com.generated.microservice.controller;

import com.generated.microservice.dto.ExternalEmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import com.generated.microservice.service.ExternalApiService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ExternalApiService externalApiService;
    private final EmployeeService employeeService;

    @PostMapping("/fetch-and-save-employees")
    public ResponseEntity<String> fetchAndSaveEmployees() {
        List<ExternalEmployeeDTO> externalEmployeeDTOs = externalApiService.fetchEmployeeData();

        List<Employee> employees = externalEmployeeDTOs.stream()
                .map(this::convertToEmployee)
                .collect(Collectors.toList());

        employeeService.saveEmployees(employees);

        return new ResponseEntity<>("Employees fetched and saved successfully", HttpStatus.OK);
    }

    private Employee convertToEmployee(ExternalEmployeeDTO externalEmployeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(externalEmployeeDTO.getEmployeeId());
        employee.setName(externalEmployeeDTO.getName());
        employee.setContactInformation(externalEmployeeDTO.getContactInformation());
        return employee;
    }
}