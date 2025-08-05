package com.generated.microservice.service;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.source.PurchaseOrder;

import java.io.IOException;

public interface JsonDataTransformerService {

    String transform(String sourceXml, String mappingJson) throws IOException;

    PurchaseOrder transformPOJO(Order order, String mappingJson) throws IOException;
    String generateXml(PurchaseOrder purchaseOrder) throws IOException;

}