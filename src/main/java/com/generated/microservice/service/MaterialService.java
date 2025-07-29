package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialDTO;
import java.util.List;

public interface MaterialService {
    List<MaterialDTO> getMaterialsByEmployeeId(String employeeId);
}