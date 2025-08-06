package com.generated.microservice.model.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PurchaseOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrder {

    @XmlElement(name = "Id")
    private String id;

    @XmlElement(name = "Date")
    private String date;

    @XmlElement(name = "Client")
    private Client client;

    @XmlElement(name = "Products")
    private Products products;

    @XmlElement(name = "Payment")
    private Payment payment;
}
--- FILE END: src/main/java/com/generated/microservice/model/target/PurchaseOrder.java ---