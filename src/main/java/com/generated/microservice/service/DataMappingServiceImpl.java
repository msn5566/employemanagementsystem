package com.generated.microservice.service;

import com.generated.microservice.model.SourceData;
import com.generated.microservice.model.TargetData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DataMappingServiceImpl implements DataMappingService {

    private final JsonDataTransformerService jsonDataTransformerService;

    @Override
    public TargetData mapSourceToTarget(SourceData sourceData, String mappingJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        TargetData targetData = new TargetData();

        try {
            JsonNode mappingNode = objectMapper.readTree(mappingJson);

            // Use JsonDataTransformerService to transform data based on the mapping
            String idMapping = mappingNode.get("id").asText();
            String nameMapping = mappingNode.get("name").asText();
            String teamMapping = mappingNode.get("team").asText();

            targetData.setId(jsonDataTransformerService.transform(idMapping, sourceData));
            targetData.setName(jsonDataTransformerService.transform(nameMapping, sourceData));
            targetData.setTeam(jsonDataTransformerService.transform(teamMapping, sourceData));


        } catch (IOException e) {
            throw new RuntimeException("Error processing mapping JSON", e);
        }

        return targetData;
    }
}