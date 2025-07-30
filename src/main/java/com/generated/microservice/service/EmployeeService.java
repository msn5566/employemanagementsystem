package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    void fetchDataAndSave();

    String addEmployee(EmployeeDTO employeeDTO);

}