package com.generated.microservice.model.source;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private String Email;
    private String Phone;

    @XmlElement(name = "Email")
    public String getEmail() {
        return Email;
    }

    @XmlElement(name = "Phone")
    public String getPhone() {
        return Phone;
    }
}