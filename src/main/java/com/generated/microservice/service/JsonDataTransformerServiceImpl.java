package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {


    private final ObjectMapper objectMapper;

    public JsonDataTransformerServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String transform(String sourceXml, String mappingJson) {
        try {
            // 1. Unmarshal Source XML
            JAXBContext sourceJaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller sourceUnmarshaller = sourceJaxbContext.createUnmarshaller();
            Order sourceOrder = (Order) sourceUnmarshaller.unmarshal(new StringReader(sourceXml));


            // 2. Parse Mapping JSON
            JsonNode mappingRoot = objectMapper.readTree(mappingJson);
            JsonNode mappings = mappingRoot.path("mappings");

            // 3. Create Target Object and Apply Mappings
            PurchaseOrder targetPurchaseOrder = new PurchaseOrder();

                // Example Mapping (Adapt for all mappings)
            for (JsonNode mapping : mappings) {
                String sourceField = mapping.path("sourceField").asText();
                String targetField = mapping.path("targetField").asText();
                 // ... (Implementation for applying each mapping rule)

                if (sourceField.equals("Order.Customer.Name") && targetField.equals("PurchaseOrder.Client.FullName")) {
                    targetPurchaseOrder.getClient().setFullName(sourceOrder.getCustomer().getName());
                }

                 if (sourceField.equals("Order.Billing.Paid") && targetField.equals("PurchaseOrder.Payment.Status")) {

                    targetPurchaseOrder.getPayment().setStatus(booleanToPaidStatus(sourceOrder.getBilling().isPaid()));
                }


            }


            // 4. Marshal Target XML
            JAXBContext targetJaxbContext = JAXBContext.newInstance(PurchaseOrder.class);
            Marshaller targetMarshaller = targetJaxbContext.createMarshaller();
            targetMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // For pretty printing

            StringWriter targetXmlWriter = new StringWriter();
            targetMarshaller.marshal(targetPurchaseOrder, targetXmlWriter);

            return targetXmlWriter.toString();

        } catch (JAXBException e) {
            log.error("JAXBException during transformation: {}", e.getMessage(), e);
            return null;
        } catch (IOException e) {
            log.error("IOException during transformation: {}", e.getMessage(), e);
            return null;
        } catch (Exception e) {  // Catching general Exception for demonstration
            log.error("Exception during transformation: {}", e.getMessage(), e); // Log the full exception
            return null; // Or throw a custom exception
        }
    }



    private String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }


}