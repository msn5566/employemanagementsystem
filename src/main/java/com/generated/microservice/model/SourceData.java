package com.generated.microservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "source")
@XmlAccessorType(XmlAccessType.FIELD)
public class SourceData {

    @XmlElement(name = "employeeId")
    private String employeeId;

    @XmlElement(name = "employeeName")
    private String employeeName;

    @XmlElement(name = "department")
    private String department;
}