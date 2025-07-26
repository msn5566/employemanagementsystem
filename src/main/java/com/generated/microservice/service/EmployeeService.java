package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    List<Employee> findEmployeesByPhoto(MultipartFile photo);
}