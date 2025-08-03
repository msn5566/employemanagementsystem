package com.generated.microservice.controller;

import com.generated.microservice.service.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mapping")
@RequiredArgsConstructor
public class MappingController {

    private final MappingService mappingService;

    @PostMapping("/process")
    public ResponseEntity<Object> processMapping(@RequestParam("sourceXml") MultipartFile sourceXml, @RequestParam("mappingCsv") MultipartFile mappingCsv) {
        try {
            Object mappedPojo = mappingService.mapData(sourceXml, mappingCsv);
            return ResponseEntity.ok(mappedPojo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing files: " + e.getMessage());
        }
    }
}