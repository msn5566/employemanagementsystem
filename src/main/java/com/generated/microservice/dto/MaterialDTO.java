package com.generated.microservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaterialDTO {
    private String id;
    private String name;
    private int quantity;
    private LocalDate issueDate;
    private String employeeId;
}