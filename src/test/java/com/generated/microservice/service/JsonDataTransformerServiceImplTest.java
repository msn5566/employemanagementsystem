
package com.generated.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JsonDataTransformerServiceImplTest {

    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @Mock
    private ObjectMapper objectMapper; // Mock ObjectMapper


    @Test
    void testTransform_invalidInputJAXBException() throws IOException {
        String invalidXml = "<invalid>xml</invalid>";
        String mappingJson = "{}";

        assertThrows(IOException.class, () -> {
            jsonDataTransformerService.transformFromFiles(
                new org.springframework.mock.web.MockMultipartFile("source", "source.xml", "text/xml", invalidXml.getBytes()),
                new org.springframework.mock.web.MockMultipartFile("target", "target.xml", "text/xml", "<purchaseOrder></purchaseOrder>".getBytes()),
                new org.springframework.mock.web.MockMultipartFile("mapping", "mapping.json", "application/json", "{}".getBytes())
            );
        });
    }


    @Test
    void testTransformPOJO() throws IOException {
        // Sample source and target objects (replace with actual data)
        Order sourceOrder = new Order();
        // ... populate sourceOrder ...

        String mappingJson = "{}";  // Or provide a relevant mapping JSON string

        PurchaseOrder result = jsonDataTransformerService.transformPOJO(sourceOrder, mappingJson);
        // Assert the transformed object
        // ... assert values in 'result' according to mappingJson ...
    }

}