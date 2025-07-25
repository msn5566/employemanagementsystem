package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    // Service method to find employees by photo
    List<Employee> findEmployeesByPhoto(String photo);
}