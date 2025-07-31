package com.generated.microservice.service;

import com.generated.microservice.dto.SourceDataDTO;
import com.generated.microservice.dto.TargetDataDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class XmlDataServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private XmlDataServiceImpl xmlDataService;

    private MultipartFile sourceXmlFile;
    private MultipartFile targetXmlFile;
    private MultipartFile mappingCsvFile;

    @BeforeEach
    void setUp() throws Exception {
        String sourceXmlContent = "<employee><employeeId>123</employeeId><name>John Doe</name><contactInformation>john.doe@example.com</contactInformation></employee>";
        String targetXmlContent = "<employee><formattedEmployeeId>123-formatted</formattedEmployeeId><fullName>John Doe</fullName></employee>";
        String mappingCsvContent = "source,target\nemployeeId,formattedEmployeeId\nname,fullName";

        sourceXmlFile = createMultipartFile("source.xml", sourceXmlContent);
        targetXmlFile = createMultipartFile("target.xml", targetXmlContent);
        mappingCsvFile = createMultipartFile("mapping.csv", mappingCsvContent);
    }

    private MultipartFile createMultipartFile(String filename, String content) throws Exception {
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(contentBytes);
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        when(multipartFile.getInputStream()).thenReturn(inputStream);
        when(multipartFile.getOriginalFilename()).thenReturn(filename);
        return multipartFile;
    }

    @Test
    void processXmlData_Success() {
        when(mongoTemplate.collectionExists(anyString())).thenReturn(false);

        xmlDataService.processXmlData(sourceXmlFile, targetXmlFile, mappingCsvFile);

        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(mongoTemplate, times(1)).collectionExists("employees");
        verify(mongoTemplate, times(1)).createCollection("employees");
    }

    @Test
    void processXmlData_CollectionExists() {
        when(mongoTemplate.collectionExists(anyString())).thenReturn(true);

        xmlDataService.processXmlData(sourceXmlFile, targetXmlFile, mappingCsvFile);

        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(mongoTemplate, times(1)).collectionExists("employees");
        verify(mongoTemplate, never()).createCollection("employees");
    }

}