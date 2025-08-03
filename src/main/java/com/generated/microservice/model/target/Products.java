package com.generated.microservice.model.target;

import lombok.Data;
import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;

@Data
public class Products {

    @XmlElement(name = "Product")
    private List<Product> product;
}