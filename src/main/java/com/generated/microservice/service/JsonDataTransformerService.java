package com.generated.microservice.service;

import com.generated.microservice.model.target.PurchaseOrder;

public interface JsonDataTransformerService {
    String transform(String xmlData);
    PurchaseOrder transformToPurchaseOrder(String xmlSource, String mappingJson);
}