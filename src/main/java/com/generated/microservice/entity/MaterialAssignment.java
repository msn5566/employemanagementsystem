package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "material_assignments")
public class MaterialAssignment {

    @Id
    private String id;
    private String employeeId;
    private String materialId;
    private String assignmentDate; // Consider using LocalDate or Date
}