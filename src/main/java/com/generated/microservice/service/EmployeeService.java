package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.MaterialIssue;

import java.util.List;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

    List<MaterialIssue> getMaterialsByEmployeeId(String employeeId);
}