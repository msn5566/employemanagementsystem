package com.generated.microservice.model.target;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PurchaseOrder")
public class PurchaseOrder {

    @XmlElement(name = "Id")
    private String Id;

    @XmlElement(name = "Date")
    private String Date;

    @XmlElement(name = "Client")
    private Client Client;


    @XmlElement(name = "Products")
    private Products Products;

    @XmlElement(name = "Payment")
    private Payment Payment;
}