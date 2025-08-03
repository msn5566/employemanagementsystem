package com.generated.microservice.model.target;

import lombok.Data;
import jakarta.xml.bind.annotation.XmlElement;

@Data
public class Client {

    @XmlElement(name = "Id")
    private String id;

    @XmlElement(name = "FullName")
    private String fullName;

    @XmlElement(name = "Email")
    private String email;

    @XmlElement(name = "Phone")
    private String phone;
}