package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    private List<Product> Product;

    @XmlElement(name = "Product")
    public List<Product> getProduct() {
        return Product;
    }
}