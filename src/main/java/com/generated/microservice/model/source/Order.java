package com.generated.microservice.model.source;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Order")
public class Order {

    private String orderId;

    private String orderDate;

    private Customer customer;

    private Items items;

    private Billing billing;

    @XmlElement(name = "OrderId")
    public String getOrderId() {
        return orderId;
    }

    @XmlElement(name = "OrderDate")
    public String getOrderDate() {
        return orderDate;
    }

    @XmlElement(name = "Customer")
    public Customer getCustomer() {
        return customer;
    }

    @XmlElement(name = "Items")
    public Items getItems() {
        return items;
    }

    @XmlElement(name = "Billing")
    public Billing getBilling() {
        return billing;
    }
}