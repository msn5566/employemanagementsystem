package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.MaterialDTO;

import java.util.List;

public interface EmployeeService {

    List<MaterialDTO> getEmployeeMaterials(String employeeId);

    String addEmployee(EmployeeDTO employeeDTO);

}