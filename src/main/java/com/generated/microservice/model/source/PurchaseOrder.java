package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PurchaseOrder")
public class PurchaseOrder {

    private String Id;

    private String Date;

    private Client Client;

    private Products Products;

    private Payment Payment;

    @XmlElement(name = "Id")
    public String getId() {
        return Id;
    }

    @XmlElement(name = "Date")
    public String getDate() {
        return Date;
    }

    @XmlElement(name = "Client")
    public Client getClient() {
        return Client;
    }

    @XmlElement(name = "Products")
    public Products getProducts() {
        return Products;
    }

    @XmlElement(name = "Payment")
    public Payment getPayment() {
        return Payment;
    }
}