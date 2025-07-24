package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }
}