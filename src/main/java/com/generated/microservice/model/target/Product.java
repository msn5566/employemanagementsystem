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
    private String Code;

    @XmlElement(name = "Name")
    private String Name;

    @XmlElement(name = "Qty")
    private Integer Qty;

    @XmlElement(name = "UnitPrice")
    private Double UnitPrice;

}