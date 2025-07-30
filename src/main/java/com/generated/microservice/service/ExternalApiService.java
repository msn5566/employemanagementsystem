package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;

import java.util.List;

public interface ExternalApiService {
    List<EmployeeDTO> fetchEmployeeData();
}