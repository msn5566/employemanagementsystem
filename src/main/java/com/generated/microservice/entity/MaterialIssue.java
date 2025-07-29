package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "material_issues")
public class MaterialIssue {

    @Id
    private String id;
    private String employeeId;
    private String materialName;
    private int quantity;
    private Date issueDate;
}