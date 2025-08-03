package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.TargetData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DataMappingServiceImplTest {

    @Mock
    private JsonDataTransformer jsonDataTransformer;

    @InjectMocks
    private DataMappingServiceImpl dataMappingService;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mappingConfig = objectMapper.readTree(new File("src/main/resources/mapping_with_validation.json"));
        lenient().doReturn(mappingConfig).when(jsonDataTransformer).transform(null, null); // Stubbing might not be needed
        dataMappingService.init(); // Initialize mappingConfig here
    }

    @Test
    void transformData_shouldMapDataCorrectly() {
        TargetData targetData = dataMappingService.transformData();

        assertNotNull(targetData);
        assertEquals("123", targetData.getEmployeeId());
        assertEquals("John Doe", targetData.getEmployeeName());
        assertEquals("IT", targetData.getDepartment());
    }
}