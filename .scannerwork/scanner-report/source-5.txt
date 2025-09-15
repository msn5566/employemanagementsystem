package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;
    private String employeeId;
    private String name;
    private String contactInformation;

}