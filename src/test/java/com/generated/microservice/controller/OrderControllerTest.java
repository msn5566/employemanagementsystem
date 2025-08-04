package com.generated.microservice.controller;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import com.generated.microservice.service.JsonDataTransformerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JsonDataTransformerService jsonDataTransformerService;

    @InjectMocks
    private OrderController orderController;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void transformOrder() {
        Order order = new Order();
        String mappingJson = "{}";
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        when(jsonDataTransformerService.transform(order, mappingJson)).thenReturn(purchaseOrder);

        ResponseEntity<PurchaseOrder> response = orderController.transformOrder(order, mappingJson);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(purchaseOrder, response.getBody());

    }
}