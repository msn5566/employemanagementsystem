package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "materials")
public class Material {
    private String id;
    private String itemName;
    private String issueDate;
    private String notes;
    private String employeeId;
}