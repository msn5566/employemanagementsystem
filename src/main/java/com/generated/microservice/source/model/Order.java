package com.generated.microservice.source.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Order")
public class Order {

    @XmlElement(name = "OrderId")
    private String orderId;

    @XmlElement(name = "OrderDate")
    private String orderDate;

    @XmlElement(name = "Customer")
    private Customer customer;

    @XmlElement(name = "Items")
    private Items items;

    @XmlElement(name = "Billing")
    private Billing billing;
}