package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.TargetData;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DataMappingServiceImpl implements DataMappingService {

    private final JsonDataTransformer jsonDataTransformer;
    private JsonNode mappingConfig;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            mappingConfig = objectMapper.readTree(new File("src/main/resources/mapping_with_validation.json"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load mapping configuration", e);
        }
    }

    @Override
    public TargetData transformData() {
        try {
            File xmlFile = new File("src/main/resources/source.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("employee");
            if (nList.getLength() > 0) {
                Node node = nList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String employeeId = element.getElementsByTagName("id").item(0).getTextContent();
                    String employeeName = element.getElementsByTagName("name").item(0).getTextContent();
                    String department = element.getElementsByTagName("department").item(0).getTextContent();

                    TargetData targetData = new TargetData();
                    targetData.setEmployeeId(employeeId);
                    targetData.setEmployeeName(employeeName);
                    targetData.setDepartment(department);

                    return targetData;
                }
            }

            return null;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException("Error processing XML file", e);
        }
    }
}