package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;

import java.io.ByteArrayInputStream;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);
    ByteArrayInputStream exportToExcel();
}