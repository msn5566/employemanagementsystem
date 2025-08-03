package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class JsonDataTransformer {

    public JsonNode transform(JsonNode source, JsonNode mappingConfig) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode target = mapper.createObjectNode();

        Map<String, String> mapping = mapper.convertValue(mappingConfig.get("mapping"), Map.class);

        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            String targetField = entry.getKey();
            String sourcePath = entry.getValue();

            JsonNode sourceValue = source.at(sourcePath.replace(".", "/"));
            ((com.fasterxml.jackson.databind.node.ObjectNode) target).set(targetField, sourceValue);
        }

        return target;
    }
}