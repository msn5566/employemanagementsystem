package com.generated.microservice.model.source;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billing {

    private String customerId;

    private double totalAmount;

    private boolean paid;

    @XmlElement(name = "CustomerId")
    public String getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "TotalAmount")
    public double getTotalAmount() {
        return totalAmount;
    }

    @XmlElement(name = "Paid")
    public boolean isPaid() {
        return paid;
    }
}