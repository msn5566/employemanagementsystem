package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String Code;

    private String Name;

    private int Qty;

    private double UnitPrice;

    @XmlElement(name = "Code")
    public String getCode() {
        return Code;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return Name;
    }

    @XmlElement(name = "Qty")
    public int getQty() {
        return Qty;
    }

    @XmlElement(name = "UnitPrice")
    public double getUnitPrice() {
        return UnitPrice;
    }
}