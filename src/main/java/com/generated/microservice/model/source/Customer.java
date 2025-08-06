package com.generated.microservice.model.source;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String customerId;

    private String name;

    private Contact contact;

    @XmlElement(name = "CustomerId")
    public String getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "Contact")
    public Contact getContact() {
        return contact;
    }
}