package com.generated.microservice.service;

import com.generated.microservice.dto.SourceDataDTO;
import com.generated.microservice.dto.TargetDataDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.mongodb.core.MongoTemplate;
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
public class XmlDataServiceImpl implements XmlDataService {

    private final EmployeeRepository employeeRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void processXmlData(MultipartFile sourceXmlFile, MultipartFile targetXmlFile, MultipartFile mappingCsvFile) {
        try {
            SourceDataDTO sourceData = parseSourceXml(sourceXmlFile);
            TargetDataDTO targetData = parseTargetXml(targetXmlFile);
            Map<String, String> mapping = parseMappingCsv(mappingCsvFile);

            // Transform source data based on mapping
            String formattedEmployeeId = applyMapping(sourceData.getEmployeeId(), mapping);
            String fullName = applyMapping(sourceData.getName(), mapping);

            // Create Employee entity and save to MongoDB
            Employee employee = new Employee();
            employee.setEmployeeId(sourceData.getEmployeeId());
            employee.setName(sourceData.getName());
            employee.setContactInformation(sourceData.getContactInformation());
            employeeRepository.save(employee);

            // Optionally, use mongoTemplate to check and create collection if not exists.
            if (!mongoTemplate.collectionExists("employees")) {
                mongoTemplate.createCollection("employees");
            }


        } catch (Exception e) {
            throw new RuntimeException("Error processing XML data", e);
        }
    }

    private SourceDataDTO parseSourceXml(MultipartFile file) throws Exception {
        SourceDataDTO sourceData = new SourceDataDTO();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file.getInputStream());
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("employee"); // Assuming "employee" is the root tag
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sourceData.setEmployeeId(eElement.getElementsByTagName("employeeId").item(0).getTextContent());
                sourceData.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                sourceData.setContactInformation(eElement.getElementsByTagName("contactInformation").item(0).getTextContent());
            }
        }
        return sourceData;
    }

    private TargetDataDTO parseTargetXml(MultipartFile file) throws Exception {
        TargetDataDTO targetData = new TargetDataDTO();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file.getInputStream());
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("employee"); // Assuming "employee" is the root tag
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                targetData.setFormattedEmployeeId(eElement.getElementsByTagName("formattedEmployeeId").item(0).getTextContent());
                targetData.setFullName(eElement.getElementsByTagName("fullName").item(0).getTextContent());
            }
        }
        return targetData;
    }


    private Map<String, String> parseMappingCsv(MultipartFile file) throws Exception {
        Map<String, String> mapping = new HashMap<>();
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader("source", "target")
                    .withSkipHeaderRecord());

            for (CSVRecord csvRecord : csvParser) {
                String source = csvRecord.get("source");
                String target = csvRecord.get("target");
                mapping.put(source, target);
            }
        }
        return mapping;
    }

    private String applyMapping(String sourceValue, Map<String, String> mapping) {
        // In a real application, you would use the mapping to transform the data.
        // For this example, we simply return the source value.
        return sourceValue;
    }
}