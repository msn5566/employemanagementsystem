package com.generated.microservice.model.source;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @XmlElement(name = "Item")
    private List<Item> item;
}