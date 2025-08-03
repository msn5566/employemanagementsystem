package com.generated.microservice.service;

import com.generated.microservice.service.JsonDataTransformer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {

    private final JsonDataTransformer jsonDataTransformer;

    @Override
    public Object mapData(MultipartFile sourceXmlFile, MultipartFile mappingCsvFile) throws Exception {
        Document sourceXml = parseXmlFile(sourceXmlFile);
        Map<String, String> mappingRules = parseMappingCsv(mappingCsvFile);

        Map<String, Object> mappedData = transformData(sourceXml, mappingRules);

        return mappedData;
    }

    private Document parseXmlFile(MultipartFile file) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(file.getInputStream());
    }

    private Map<String, String> parseMappingCsv(MultipartFile file) throws Exception {
        Map<String, String> mapping = new HashMap<>();
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                String sourceField = record.get("source_field");
                String targetField = record.get("target_field");
                mapping.put(sourceField, targetField);
            }
        }
        return mapping;
    }

    private Map<String, Object> transformData(Document sourceXml, Map<String, String> mappingRules) {
        Map<String, Object> transformedData = new HashMap<>();
        mappingRules.forEach((source, target) -> {
            String value = extractValueFromXml(sourceXml, source);
            if (value != null) {
                transformedData.put(target, value);
            }
        });
        return transformedData;
    }

    private String extractValueFromXml(Document doc, String sourceField) {
        NodeList nodeList = doc.getElementsByTagName(sourceField);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }
}