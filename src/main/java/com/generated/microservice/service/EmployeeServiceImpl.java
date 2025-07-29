package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.MaterialIssue;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.repository.MaterialIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MaterialIssueRepository materialIssueRepository;

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
    public List<MaterialIssue> getMaterialsByEmployeeId(String employeeId) {
        return materialIssueRepository.findByEmployeeId(employeeId);
    }
}