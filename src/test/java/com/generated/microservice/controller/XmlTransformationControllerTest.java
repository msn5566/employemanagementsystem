
package com.generated.microservice.controller;

import com.generated.microservice.model.target.PurchaseOrder;
import com.generated.microservice.service.JsonDataTransformerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class XmlTransformationControllerTest {

    @Mock
    private JsonDataTransformerService jsonDataTransformerService;

    @InjectMocks
    private XmlTransformationController xmlTransformationController;

    @BeforeEach
    void setUp() {
        // Removed: MockitoAnnotations.openMocks(this); // Redundant with @ExtendWith
    }

    @Test
    void shouldTransformXml_whenValidFilesProvided() throws IOException {
        // Sample files (replace with actual data)
        MockMultipartFile sourceFile = new MockMultipartFile("source", "source.xml", "text/xml", "<order></order>".getBytes());
        MockMultipartFile targetFile = new MockMultipartFile("target", "target.xml", "text/xml", "<purchaseOrder></purchaseOrder>".getBytes());
        MockMultipartFile mappingFile = new MockMultipartFile("mapping", "mapping.json", "application/json", "{}".getBytes());

        PurchaseOrder mockPurchaseOrder = new PurchaseOrder(); // Mock the transformed PurchaseOrder
        when(jsonDataTransformerService.transformFromFiles(sourceFile, targetFile, mappingFile)).thenReturn(mockPurchaseOrder);

        ResponseEntity<String> response = xmlTransformationController.transformXml(sourceFile, targetFile, mappingFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(jsonDataTransformerService, times(1)).transformFromFiles(sourceFile, targetFile, mappingFile);
    }


    @Test
    void shouldThrowException_whenIOExceptionOccurs() throws IOException {
        // Sample files (replace with actual data)
        MockMultipartFile sourceFile = new MockMultipartFile("source", "source.xml", "text/xml", "<order></order>".getBytes());
        MockMultipartFile targetFile = new MockMultipartFile("target", "target.xml", "text/xml", "<purchaseOrder></purchaseOrder>".getBytes());
        MockMultipartFile mappingFile = new MockMultipartFile("mapping", "mapping.json", "application/json", "{}".getBytes());

        when(jsonDataTransformerService.transformFromFiles(sourceFile, targetFile, mappingFile)).thenThrow(new IOException("Simulated IO Exception"));


        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            xmlTransformationController.transformXml(sourceFile, targetFile, mappingFile);
        });

        assertEquals("Error during XML transformation", thrown.getReason());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, thrown.getStatusCode());

        verify(jsonDataTransformerService, times(1)).transformFromFiles(sourceFile, targetFile, mappingFile);

    }
}