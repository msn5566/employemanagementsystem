package com.generated.microservice.model.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
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
--- FILE END: src/main/java/com/generated/microservice/model/target/Client.java ---