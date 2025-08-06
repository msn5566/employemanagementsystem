package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;

import java.io.IOException;

public interface JsonDataTransformerService {
    String transform(String sourceXml, String mappingJson) throws IOException;
     PurchaseOrder transformPOJO(Order order, JsonNode mapping) throws IOException;
    String booleanToPaidStatus(boolean paid);


}