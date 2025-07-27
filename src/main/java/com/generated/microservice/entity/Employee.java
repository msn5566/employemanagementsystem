package com.generated.microservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Data
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;
    private String employeeId;
    private String name;
    private String contactInformation;
    private List<Reward> rewards = new ArrayList<>();

    @Getter
    @Setter
    public static class Reward {
        private String description;
        private LocalDate date;
        private Double value;
    }
}