package com.generated.microservice.controller;

import com.generated.microservice.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/external-api")
@RequiredArgsConstructor
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    @PostMapping("/sync")
    public ResponseEntity<String> synchronizeData() {
        externalApiService.fetchAndSaveData();
        return ResponseEntity.ok("Data synchronization started.");
    }
}