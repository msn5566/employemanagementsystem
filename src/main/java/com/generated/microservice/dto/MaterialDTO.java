package com.generated.microservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialDTO {
    private String id;
    private String materialName;
    private Date issueDate;
    private String description;
}