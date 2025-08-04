package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JsonDataTransformerServiceImplTest {

    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @Mock
    private XmlMapper xmlMapper;

    @Mock
    private ObjectMapper objectMapper;


    @Test
    void transformData() throws IOException {
        String sourceXml = "<root><employee><name>John Doe</name><id>123</id></employee></root>";

        // Use XmlMapper to parse XML, not ObjectMapper
        JsonNode jsonNode = new XmlMapper().readTree(sourceXml.getBytes());


        Mockito.when(xmlMapper.readTree(sourceXml.getBytes())).thenReturn(jsonNode);



        Root root = jsonDataTransformerService.transformData(sourceXml);
        assertNotNull(root);
        assertEquals("John Doe", root.getEmployee().getName());
        assertEquals(123, root.getEmployee().getId());
    }

    @Test
    void transformData_withException() throws IOException {
        String sourceXml = "<root><employee><name>John Doe</name><id>123</id></employee></root>"; // Added root element

        Mockito.when(xmlMapper.readTree(sourceXml.getBytes())).thenThrow(new IOException("Error reading XML"));

        assertThrows(RuntimeException.class, () -> jsonDataTransformerService.transformData(sourceXml));


    }

    @Test
    void transformData_withDifferentException() throws IOException {
        String sourceXml = "<root><employee><name>John Doe</name><id>123</id></employee></root>"; 

        Mockito.when(xmlMapper.readTree(sourceXml.getBytes())).thenThrow(new NullPointerException("XML is null"));

        assertThrows(RuntimeException.class, () -> jsonDataTransformerService.transformData(sourceXml));
    }
}