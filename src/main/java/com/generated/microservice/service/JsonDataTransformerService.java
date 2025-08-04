package com.generated.microservice.service;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;

public interface JsonDataTransformerService {
    PurchaseOrder transform(Order sourceOrder);

    PurchaseOrder transform(Order sourceOrder, String mappingJson);
}