package com.generated.microservice.model.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
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
--- FILE END: src/main/java/com/generated/microservice/model/target/Product.java ---