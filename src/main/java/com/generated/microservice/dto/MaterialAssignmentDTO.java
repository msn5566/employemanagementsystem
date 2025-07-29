package com.generated.microservice.dto;

import lombok.Data;

@Data
public class MaterialAssignmentDTO {
    private String id;
    private String employeeId;
    private String materialId;
    private String assignmentDate;
    private MaterialDTO material;
}