package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

    void exportEmployeesToExcel(HttpServletResponse response) throws IOException;
}