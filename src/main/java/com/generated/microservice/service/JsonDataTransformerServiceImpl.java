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
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {


    private final ObjectMapper objectMapper;

    //Add this field injection
    private final OrderTransformationService orderTransformationService;

    public JsonDataTransformerServiceImpl(ObjectMapper objectMapper, OrderTransformationService orderTransformationService) {
        this.objectMapper = objectMapper;
        this.orderTransformationService = orderTransformationService;
    }


    @Override
    public String transform(String sourceXml) {
        try {
            // 1. Unmarshal Source XML
            JAXBContext sourceJaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller sourceUnmarshaller = sourceJaxbContext.createUnmarshaller();
            Order sourceOrder = (Order) sourceUnmarshaller.unmarshal(new StringReader(sourceXml));

            // 2. Apply Mapping & Transformations (Simplified - expand as needed)
            PurchaseOrder targetPurchaseOrder = orderTransformationService.transformOrder(sourceOrder);
            
            // 3. Marshal Target XML
            JAXBContext targetJaxbContext = JAXBContext.newInstance(PurchaseOrder.class);
            Marshaller targetMarshaller = targetJaxbContext.createMarshaller();
            targetMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            targetMarshaller.marshal(targetPurchaseOrder, sw);
            return sw.toString();


        } catch (JAXBException e) {
            log.error("Error during XML processing", e);
            return null; // Or handle the exception as needed
        }
    }



    // Example transformation function
    private String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }

    // ... other transform functions (TO_INT, TO_STRING, TO_DECIMAL, CONCAT)


}