package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rewards")
public class Reward {

    private String id;
    private String employeeId;
    private String name;
    private String description;
    private String date;
}