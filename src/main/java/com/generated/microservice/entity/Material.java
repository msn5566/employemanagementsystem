package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "materials")
public class Material {

    @Id
    private String id;
    private String name;
    private String description;
}