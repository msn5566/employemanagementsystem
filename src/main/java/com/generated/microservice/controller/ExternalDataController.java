package com.generated.microservice.controller;

import com.generated.microservice.service.ExternalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external-data")
@RequiredArgsConstructor
public class ExternalDataController {

    private final ExternalDataService externalDataService;

    @PostMapping("/sync")
    public ResponseEntity<String> synchronizeData() {
        externalDataService.synchronizeData();
        return ResponseEntity.ok("Data synchronization started.");
    }
}