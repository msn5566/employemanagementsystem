package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public List<Employee> findEmployeesByPhoto(MultipartFile photo) {
        // TODO: Implement the logic to compare the input photo against stored employee photos.
        // This is a placeholder implementation that returns an empty list.
        // In a real-world scenario, you would use image processing techniques
        // to compare the photos and return a list of employees whose photos closely match.
        return new ArrayList<>();
    }
}