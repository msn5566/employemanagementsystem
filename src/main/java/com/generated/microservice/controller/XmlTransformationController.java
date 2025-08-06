
package com.generated.microservice.controller;

import com.generated.microservice.model.target.PurchaseOrder;
import com.generated.microservice.service.JsonDataTransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.Instant;

@RestController
@RequestMapping("/transform")
@RequiredArgsConstructor
public class XmlTransformationController {

    private final JsonDataTransformerService jsonDataTransformerService;

    @PostMapping("/xml")
    public ResponseEntity<String> transformXml(
            @RequestParam("source") MultipartFile sourceXmlFile,
            @RequestParam("target") MultipartFile targetXmlFile,
            @RequestParam("mapping") MultipartFile mappingJsonFile) {

        try {
            PurchaseOrder transformedPurchaseOrder = jsonDataTransformerService.transformFromFiles(sourceXmlFile, targetXmlFile, mappingJsonFile);

            String timestamp = Instant.now().toString().replace(":", "_").replace(".", "_"); // Safe timestamp for filename
            String targetFileName = "target_" + timestamp + ".xml";
            // Code to generate and save XML file from PurchaseOrder (transformedPurchaseOrder) with filename targetFileName

            return ResponseEntity.ok(targetFileName); // Or appropriate success response
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during XML transformation", e);
        }

    }
}