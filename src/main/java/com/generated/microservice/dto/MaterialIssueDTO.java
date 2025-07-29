package com.generated.microservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialIssueDTO {
    private String materialName;
    private Date issueDate;
    private int quantity;
}