package com.generated.microservice.service;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;

public interface JsonDataTransformerService {
     String transform(String sourceXml, String mappingJson) throws JAXBException, IOException;


}