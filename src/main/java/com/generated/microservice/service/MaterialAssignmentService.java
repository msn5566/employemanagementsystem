package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialAssignmentDTO;

import java.util.List;

public interface MaterialAssignmentService {
    List<MaterialAssignmentDTO> getMaterialAssignmentsByEmployeeId(String employeeId);
}