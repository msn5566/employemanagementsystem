package com.generated.microservice.dto;

import com.generated.microservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeSimilarity {

    private Employee employee;
    private double similarityScore;
}