package com.generated.microservice.service;

import java.util.List;
import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.ExternalEmployeeDTO;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

    void syncEmployeesFromExternalApi(String apiUrl);
}