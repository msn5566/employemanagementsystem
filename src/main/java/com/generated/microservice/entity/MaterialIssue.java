package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "material_issues")
public class MaterialIssue {

    @Id
    private String id;
    @DBRef
    private Employee employee;
    @DBRef
    private Material material;
    private Date issueDate;
    private int quantity;
}