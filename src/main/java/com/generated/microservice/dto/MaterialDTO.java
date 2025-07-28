package com.generated.microservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaterialDTO {

    private String materialName;
    private String employeeId;
    private LocalDate issueDate;
}