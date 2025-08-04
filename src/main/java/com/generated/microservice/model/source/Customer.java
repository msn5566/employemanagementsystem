package com.generated.microservice.model.source;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @XmlElement(name = "CustomerId")
    private String customerId;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Contact")
    private Contact contact;
}