package com.generated.microservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.generated.microservice.dto.EmployeeDTO;

public interface EmployeeService {
    List<Employee> findEmployeesByPhoto(MultipartFile photo);

    String addEmployee(EmployeeDTO employeeDTO);
}