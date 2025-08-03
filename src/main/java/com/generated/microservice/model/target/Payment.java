package com.generated.microservice.model.target;

import lombok.Data;
import jakarta.xml.bind.annotation.XmlElement;

@Data
public class Payment {

    @XmlElement(name = "CustomerId")
    private String customerId;

    @XmlElement(name = "Amount")
    private double amount;

    @XmlElement(name = "Status")
    private String status;
}