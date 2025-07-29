package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialIssueDTO;
import java.util.List;

public interface MaterialIssueService {
    List<MaterialIssueDTO> getMaterialsIssuedByEmployeeId(String employeeId);
}