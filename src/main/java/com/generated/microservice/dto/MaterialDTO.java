package com.generated.microservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaterialDTO {

    private String id;
    private String employeeId;
    private String name;
    private LocalDate issueDate;
    private String description;
}