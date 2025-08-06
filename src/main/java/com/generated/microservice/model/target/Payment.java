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
public class Payment {

    @XmlElement(name = "CustomerId")
    private String customerId;

    @XmlElement(name = "Amount")
    private double amount;

    @XmlElement(name = "Status")
    private String status;
}
--- FILE END: src/main/java/com/generated/microservice/model/target/Payment.java ---