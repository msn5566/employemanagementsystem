package com.generated.microservice.model.source;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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