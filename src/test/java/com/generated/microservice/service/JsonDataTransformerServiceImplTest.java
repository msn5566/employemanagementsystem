package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.source.Billing;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.source.PurchaseOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import static org.mockito.Mockito.doReturn;

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
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void shouldTransform_whenValidInput() throws IOException {
        String sourceXml = "<order><orderId>123</orderId></order>";
        String mappingJson = "{}";
        Order order = new Order();
        order.setOrderId("123");

        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode mappingNode = jsonNodeFactory.objectNode();
        doReturn(mappingNode).when(objectMapper).readTree(mappingJson);

        when(jsonDataTransformerService.unmarshalSourceXml(sourceXml)).thenReturn(order);
        when(jsonDataTransformerService.transformPOJO(order, mappingJson)).thenReturn(new PurchaseOrder());
        when(jsonDataTransformerService.generateXml(new PurchaseOrder())).thenReturn("<purchaseOrder/>");


        String result = jsonDataTransformerService.transform(sourceXml, mappingJson);
        assertNotNull(result);

    }



    @Test
    void shouldTransformPOJO_whenValidInput() throws IOException {
        Order order = new Order();
        order.setOrderId("123");
        order.setOrderDate("2024-07-23");
        Billing billing = new Billing();
        billing.setCustomerId("456");
        billing.setTotalAmount(100.0);
        billing.setPaid(true);
        order.setBilling(billing);
        String mappingJson = "{}";

        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode mappingNode = jsonNodeFactory.objectNode();
        when(objectMapper.readTree(mappingJson)).thenReturn(mappingNode);

        when(jsonDataTransformerService.transformPOJO(order, mappingJson)).thenCallRealMethod();
        PurchaseOrder result = jsonDataTransformerService.transformPOJO(order, mappingJson);


        assertNotNull(result);
        assertEquals("123", result.getId());
        assertEquals("2024-07-23", result.getDate());
        assertNotNull(result.getPayment());
        assertEquals("456", result.getPayment().getCustomerId());

    }




    @Test
    void shouldGenerateXml_whenValidInput() throws IOException {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId("123");

        when(xmlMapper.writeValueAsString(purchaseOrder)).thenReturn("<purchaseOrder><Id>123</Id></purchaseOrder>");
        when(jsonDataTransformerService.generateXml(purchaseOrder)).thenCallRealMethod();
        String result = jsonDataTransformerService.generateXml(purchaseOrder);

        assertNotNull(result);
        assertEquals("<purchaseOrder><Id>123</Id></purchaseOrder>", result);


    }


    @Test
    void shouldThrowException_whenGenerateXmlFails() throws IOException{

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        when(jsonDataTransformerService.generateXml(purchaseOrder)).thenCallRealMethod();
        when(xmlMapper.writeValueAsString(purchaseOrder)).thenThrow(new IOException());


        assertThrows(IOException.class, () -> jsonDataTransformerService.generateXml(purchaseOrder));

    }


    @Test
    void shouldThrowException_whenTransformPOJOFails() throws IOException {
        Order order = new Order();
        String mappingJson = "{}";
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode mappingNode = jsonNodeFactory.objectNode();
        when(objectMapper.readTree(mappingJson)).thenReturn(mappingNode);
        when(jsonDataTransformerService.transformPOJO(order, mappingJson)).thenThrow(new IOException());

        assertThrows(IOException.class, () -> jsonDataTransformerService.transformPOJO(order, mappingJson));

    }


    @Test
    void shouldThrowException_whenTransformFails() throws IOException {

        String sourceXml = "<order><orderId>123</orderId></order>";
        String mappingJson = "{}";
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode mappingNode = jsonNodeFactory.objectNode();

        Order order = new Order();
        order.setOrderId("123");

        doReturn(order).when(jsonDataTransformerService).unmarshalSourceXml(sourceXml);
        doReturn(new PurchaseOrder()).when(jsonDataTransformerService).transformPOJO(order, mappingJson);
        doReturn("<purchaseOrder/>").when(jsonDataTransformerService).generateXml(new PurchaseOrder());
        when(jsonDataTransformerService.transform(sourceXml, mappingJson)).thenThrow(new IOException());


        assertThrows(IOException.class, () -> jsonDataTransformerService.transform(sourceXml, mappingJson));
    }




}