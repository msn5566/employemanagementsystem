package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JsonDataTransformerServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private XmlMapper xmlMapper;

    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @BeforeEach
    void setUp() {
        jsonDataTransformerService = new JsonDataTransformerServiceImpl();
    }

    @Test
    void transform_validMappingJson() throws Exception {
        String mappingJson = "{}";
        Order order = new Order();
        JsonNode jsonNode = Mockito.mock(JsonNode.class);
        PurchaseOrder mockPurchaseOrder = new PurchaseOrder();

        Mockito.when(objectMapper.readTree(mappingJson)).thenReturn(jsonNode);
        Mockito.when(xmlMapper.readValue(jsonNode.toString(), PurchaseOrder.class)).thenReturn(mockPurchaseOrder);

        PurchaseOrder result = jsonDataTransformerService.transform(order, mappingJson);

        assertNotNull(result);
        assertEquals(mockPurchaseOrder, result);
    }



    @Test
    void transform_invalidMappingJson() throws Exception {
        String mappingJson = "invalid json";
        Order order = new Order();

        Mockito.when(objectMapper.readTree(mappingJson)).thenThrow(new RuntimeException("Invalid JSON"));

        PurchaseOrder result = jsonDataTransformerService.transform(order, mappingJson);
        assertNull(result);
    }


}