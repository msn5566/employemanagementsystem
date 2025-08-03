package com.generated.microservice.controller;

import com.generated.microservice.model.TargetData;
import com.generated.microservice.service.DataMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data-mapping")
@RequiredArgsConstructor
public class DataMappingController {

    private final DataMappingService dataMappingService;

    @PostMapping("/transform")
    public ResponseEntity<TargetData> transformData() {
        TargetData transformedData = dataMappingService.transformData();
        return ResponseEntity.ok(transformedData);
    }
}