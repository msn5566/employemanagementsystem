package com.generated.microservice.model.target;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @XmlElement(name = "Code")
    private String code;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Qty")
    private Integer qty;

    @XmlElement(name = "UnitPrice")
    private Double unitPrice;
}