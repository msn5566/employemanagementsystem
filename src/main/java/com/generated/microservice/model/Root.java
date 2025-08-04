package com.generated.microservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Root {

    @XmlElement(name = "employee")
    private Employee employee;


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Employee {
        @XmlElement(name = "name")
        private String name;

        @XmlElement(name = "id")
        private int id;

        // ... other fields as per expected_target.xml
    }
}