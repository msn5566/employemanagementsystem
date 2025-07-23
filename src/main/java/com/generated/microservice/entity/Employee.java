package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Data
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String department;

    private String email;
}