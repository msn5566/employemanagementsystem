package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import java.util.List;
import com.generated.microservice.dto.EmployeeDTO;

public interface EmployeeService {

    List<Employee> saveEmployees(List<Employee> employees);

    String addEmployee(EmployeeDTO employeeDTO);

}