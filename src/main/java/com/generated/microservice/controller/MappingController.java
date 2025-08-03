package com.generated.microservice.controller;

import com.generated.microservice.model.SourceData;
import com.generated.microservice.model.TargetData;
import com.generated.microservice.service.DataMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MappingController {

    private final DataMappingService dataMappingService;

    @PostMapping("/mapData")
    public TargetData mapData(@RequestBody SourceData sourceData, @RequestParam String mappingJson) {
        return dataMappingService.mapSourceToTarget(sourceData, mappingJson);
    }
}