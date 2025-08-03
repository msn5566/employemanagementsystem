package com.generated.microservice.model.target;

import lombok.Data;
import jakarta.xml.bind.annotation.XmlElement;

@Data
public class Product {

    @XmlElement(name = "Code")
    private String code;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Qty")
    private int qty;

    @XmlElement(name = "UnitPrice")
    private double unitPrice;
}