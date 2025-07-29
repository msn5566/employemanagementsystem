package com.generated.microservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaterialDTO {
    private String materialName;
    private LocalDate issueDate;
    private String description;
}