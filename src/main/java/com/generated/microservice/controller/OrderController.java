package com.generated.microservice.controller;

import com.generated.microservice.model.target.PurchaseOrder;
import com.generated.microservice.service.JsonDataTransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final JsonDataTransformerService jsonDataTransformerService;

    @PostMapping("/transform")
    public ResponseEntity<PurchaseOrder> transformOrder(@RequestBody Map<String, String> request) {
        String xmlSource = request.get("xmlSource");
        String mappingJson = request.get("mappingJson");

        PurchaseOrder purchaseOrder = jsonDataTransformerService.transformToPurchaseOrder(xmlSource, mappingJson);
        if (purchaseOrder == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(purchaseOrder);
    }
}