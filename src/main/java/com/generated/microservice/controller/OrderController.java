package com.generated.microservice.controller;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import com.generated.microservice.service.JsonDataTransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final JsonDataTransformerService jsonDataTransformerService;

    @PostMapping("/transform")
    public ResponseEntity<PurchaseOrder> transformOrder(@RequestBody Order order, @RequestParam String mappingJson) {
        PurchaseOrder transformedOrder = jsonDataTransformerService.transform(order, mappingJson);
        return ResponseEntity.ok(transformedOrder);
    }
}