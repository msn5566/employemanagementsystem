package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private String Id;

    private String FullName;

    private String Email;

    private String Phone;

    @XmlElement(name = "Id")
    public String getId() {
        return Id;
    }

    @XmlElement(name = "FullName")
    public String getFullName() {
        return FullName;
    }

    @XmlElement(name = "Email")
    public String getEmail() {
        return Email;
    }

    @XmlElement(name = "Phone")
    public String getPhone() {
        return Phone;
    }
}