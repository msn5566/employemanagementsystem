package com.generated.microservice.controller;

import com.generated.microservice.model.target.PurchaseOrder;
import com.generated.microservice.service.JsonDataTransformerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderControllerTest {

    private MockMvc mockMvc;
    @Mock
    private JsonDataTransformerService jsonDataTransformerService;
    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }



    @Test
    void shouldTransformOrder_whenValidRequest() {
        String xmlSource = "<Order><orderId>123</orderId></Order>";
        String mappingJson = "{}";
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId("123");

        when(jsonDataTransformerService.transformToPurchaseOrder(xmlSource, mappingJson)).thenReturn(purchaseOrder);

        ResponseEntity<PurchaseOrder> response = orderController.transformOrder(Map.of("xmlSource", xmlSource, "mappingJson", mappingJson));

        assertEquals(OK, response.getStatusCode());
        assertEquals(purchaseOrder, response.getBody());
    }

    @Test
    void shouldReturnBadRequest_whenTransformationFails() {
        String xmlSource = "<InvalidOrder></InvalidOrder>";
        String mappingJson = "{}";

        when(jsonDataTransformerService.transformToPurchaseOrder(xmlSource, mappingJson)).thenReturn(null);

        ResponseEntity<PurchaseOrder> response = orderController.transformOrder(Map.of("xmlSource", xmlSource, "mappingJson", mappingJson));

        assertEquals(BAD_REQUEST, response.getStatusCode());

    }
}