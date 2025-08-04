package com.generated.microservice.model.source;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @XmlElement(name = "Email")
    private String email;

    @XmlElement(name = "Phone")
    private String phone;
}