
package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.Client;
import com.generated.microservice.model.target.Payment;
import com.generated.microservice.model.target.Products;
import com.generated.microservice.model.target.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    private final ObjectMapper objectMapper;

    @Override
    public String transform(String sourceXml, String mappingJson) throws IOException {
        Order order = objectMapper.readValue(sourceXml, Order.class);
        JsonNode mapping = objectMapper.readTree(mappingJson);

        PurchaseOrder purchaseOrder = transformPOJO(order, mapping);

        String timestamp = Instant.now().toString().replace(":", "_").replace(".", "_"); // Safe timestamp for filename
        String targetXmlFilename = "target_" + timestamp + ".xml";

        return  objectMapper.writeValueAsString(purchaseOrder);
    }

    @Override
    public PurchaseOrder transformPOJO(Order order, JsonNode mapping) throws IOException {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        if (order == null) {
            return null;
        }

        Client client = null;
        if (order.getCustomer() != null) {
            client = objectMapper.convertValue(order.getCustomer(), Client.class);
        }
        purchaseOrder.setClient(client);

        Payment payment = null;
        if (order.getBilling() != null) {
            payment = objectMapper.convertValue(order.getBilling(), Payment.class);
            if (payment != null) {
                payment.setAmount(order.getBilling().getTotalAmount());
                payment.setStatus(booleanToPaidStatus(order.getBilling().getPaid()));
            }
        }
        purchaseOrder.setPayment(payment);
        purchaseOrder.setDate(order.getOrderDate());
        purchaseOrder.setId(order.getOrderId());

        Products products = objectMapper.convertValue(order.getItems(), Products.class);
        purchaseOrder.setProducts(products);

        return purchaseOrder;
    }

    @Override
    public String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }
}