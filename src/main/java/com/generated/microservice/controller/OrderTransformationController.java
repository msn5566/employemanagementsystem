
package com.generated.microservice.controller;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import com.generated.microservice.service.JsonDataTransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transform")
@RequiredArgsConstructor
public class OrderTransformationController {

    private final JsonDataTransformerService jsonDataTransformerService;

    @PostMapping("/order")
    public ResponseEntity<PurchaseOrder> transformOrder(@RequestBody Order sourceOrder) {
        PurchaseOrder purchaseOrder = jsonDataTransformerService.transform(sourceOrder);
        return ResponseEntity.ok(purchaseOrder);
    }
}