package com.generated.microservice.model.target;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PurchaseOrder")
public class PurchaseOrder {

    @XmlElement(name = "Id")
    private String id;

    @XmlElement(name = "Date")
    private String date;

    @XmlElement(name = "Client")
    private Client client;

    @XmlElement(name = "Products")
    private Products products;

    @XmlElement(name = "Payment")
    private Payment payment;
}