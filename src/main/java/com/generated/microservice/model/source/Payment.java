package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String CustomerId;

    private double Amount;

    private String Status;

    @XmlElement(name = "CustomerId")
    public String getCustomerId() {
        return CustomerId;
    }

    @XmlElement(name = "Amount")
    public double getAmount() {
        return Amount;
    }

    @XmlElement(name = "Status")
    public String getStatus() {
        return Status;
    }
}