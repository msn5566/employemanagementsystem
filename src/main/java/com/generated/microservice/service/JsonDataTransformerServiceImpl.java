
package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.Root;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    @Override
    public Root transformData(String sourceXml) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            XmlMapper xmlMapper = new XmlMapper();

            JsonNode jsonNode = xmlMapper.readTree(sourceXml.getBytes());

            // Example transformation (replace with actual logic from mapping_with_validation.json)
            String employeeName = jsonNode.path("employee").path("name").asText();
            int employeeId = jsonNode.path("employee").path("id").asInt();

            Root root = new Root();
            root.setEmployee(new Root.Employee());
            root.getEmployee().setName(employeeName);
            root.getEmployee().setId(employeeId);
            // ... other mappings based on mapping_with_validation.json


            return root;
        } catch (IOException e) {
            throw new RuntimeException("Error transforming XML data: " + e.getMessage(), e);
        }
    }
}