package com.generated.microservice.model.source;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String itemId;

    private String description;

    private int quantity;

    private double price;

    @XmlElement(name = "ItemId")
    public String getItemId() {
        return itemId;
    }

    @XmlElement(name = "Description")
    public String getDescription() {
        return description;
    }

    @XmlElement(name = "Quantity")
    public int getQuantity() {
        return quantity;
    }

    @XmlElement(name = "Price")
    public double getPrice() {
        return price;
    }
}