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
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    private final ObjectMapper objectMapper;

    public JsonDataTransformerServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public String transform(String sourceXml, String mappingJson) throws JAXBException, IOException {
        JAXBContext jaxbContextSource = JAXBContext.newInstance(Order.class);
        Unmarshaller unmarshaller = jaxbContextSource.createUnmarshaller();
        Order sourceOrder = (Order) unmarshaller.unmarshal(new StringReader(sourceXml));

        PurchaseOrder targetOrder = transformPOJO(sourceOrder, mappingJson);

        JAXBContext jaxbContextTarget = JAXBContext.newInstance(PurchaseOrder.class);
        Marshaller marshaller = jaxbContextTarget.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // For pretty printing
        StringWriter sw = new StringWriter();
        marshaller.marshal(targetOrder, sw);
        return sw.toString();
    }

    @Override
    public PurchaseOrder transformPOJO(Order sourceOrder, String mappingJson) throws IOException {

        JsonNode mappingRoot = objectMapper.readTree(mappingJson);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        // Example transformation (Implement your complete mapping logic here)
        purchaseOrder.setId(sourceOrder.getOrderId());


        return purchaseOrder;
    }



    private String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }
    // Implement other transform functions (TO_INT, TO_STRING, TO_DECIMAL, CONCAT) similarly.
}