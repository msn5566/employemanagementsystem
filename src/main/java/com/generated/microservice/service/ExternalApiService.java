package com.generated.microservice.service;

import com.generated.microservice.dto.ExternalEmployeeDTO;
import java.util.List;

public interface ExternalApiService {

    List<ExternalEmployeeDTO> fetchEmployeeData();
}