package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

    List<Employee> findEmployeesByPhoto(MultipartFile photo);
}