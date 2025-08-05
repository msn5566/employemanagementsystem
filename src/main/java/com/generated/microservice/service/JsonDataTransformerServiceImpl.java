package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.source.Billing;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.source.PurchaseOrder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;


    @Autowired
    public JsonDataTransformerServiceImpl(ObjectMapper objectMapper, XmlMapper xmlMapper) {
        this.objectMapper = objectMapper;
        this.xmlMapper = xmlMapper;
    }

    @Override
    public String transform(String sourceXml, String mappingJson) throws IOException {
        Order order = unmarshalSourceXml(sourceXml);
        PurchaseOrder purchaseOrder = transformPOJO(order, mappingJson);
        return generateXml(purchaseOrder);
    }


    @Override
    public PurchaseOrder transformPOJO(Order order, String mappingJson) throws IOException {
        // Existing transformation logic (from previous responses) remains here

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        JsonNode mapping = objectMapper.readTree(mappingJson);

        // Example mapping (adapt as needed based on your mapping JSON structure)
        if (order != null) {
            purchaseOrder.setId(order.getOrderId());
            purchaseOrder.setDate(order.getOrderDate());
            // ... other mappings

             Billing billing = order.getBilling();
            if (billing != null) {
                purchaseOrder.setPayment(new com.generated.microservice.model.source.Payment(
                        billing.getCustomerId(),
                        billing.getTotalAmount(),
                        booleanToPaidStatus(billing.isPaid())
                ));
            }

        }


        return purchaseOrder;

    }



    public String generateXml(PurchaseOrder purchaseOrder) throws IOException {

        StringWriter sw = new StringWriter();
        xmlMapper.writeValue(sw, purchaseOrder);
        return sw.toString();
    }


    Order unmarshalSourceXml(String sourceXml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(sourceXml);
            return (Order) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            log.error("Error unmarshalling XML: {}", e.getMessage());
            return null; // Or throw an exception depending on your error handling strategy
        }
    }



    String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }


}