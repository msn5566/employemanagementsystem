package com.generated.microservice.controller;

import com.generated.microservice.service.XmlDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XmlDataController {

    private final XmlDataService xmlDataService;

    @PostMapping("/process")
    public ResponseEntity<String> processXmlData(
            @RequestParam("sourceXml") MultipartFile sourceXmlFile,
            @RequestParam("targetXml") MultipartFile targetXmlFile,
            @RequestParam("mappingCsv") MultipartFile mappingCsvFile) {
        try {
            xmlDataService.processXmlData(sourceXmlFile, targetXmlFile, mappingCsvFile);
            return ResponseEntity.ok("XML data processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing XML data: " + e.getMessage());
        }
    }
}