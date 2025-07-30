package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataSyncServiceImpl implements DataSyncService {

    private final ExternalApiService externalApiService;
    private final EmployeeRepository employeeRepository;

    @Override
    public void syncEmployeeData() {
        List<EmployeeDTO> employeeDTOs = externalApiService.fetchEmployeeData();

        List<Employee> employees = employeeDTOs.stream()
                .map(this::convertToEntity)
                .toList();

        employeeRepository.saveAll(employees);
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setContactInformation(employeeDTO.getContactInformation());
        return employee;
    }
}