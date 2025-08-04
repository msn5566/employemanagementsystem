package com.generated.microservice.model.source;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @XmlElement(name = "ItemId")
    private String itemId;

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "Quantity")
    private int quantity;

    @XmlElement(name = "Price")
    private double price;
}