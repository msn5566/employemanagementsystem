package com.generated.microservice.dto;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
public class MaterialDTO {
    @NotEmpty(message = "Material name cannot be empty")
    private String materialName;
    private String issueDate;
    private String metadata;
}