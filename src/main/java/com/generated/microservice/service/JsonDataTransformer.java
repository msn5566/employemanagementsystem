package com.generated.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Service
@Slf4j
public class JsonDataTransformer implements DataTransformer {

    @Override
    public String transform(String sourceXml, String mappingJson) {
        try {
            // Unmarshal Source XML
            Order sourceOrder = unmarshalSourceXml(sourceXml);

            // Create Target POJO
            PurchaseOrder targetPurchaseOrder = new PurchaseOrder();

            // Marshal Target XML
            return marshalTargetXml(targetPurchaseOrder);

        } catch (Exception e) {
            log.error("Error during transformation: {}", e.getMessage(), e);
            throw new RuntimeException("Error during transformation", e);
        }
    }


    private Order unmarshalSourceXml(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        return (Order) unmarshaller.unmarshal(reader);
    }

    private String marshalTargetXml(PurchaseOrder purchaseOrder) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseOrder.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // For pretty printing
        StringWriter writer = new StringWriter();
        marshaller.marshal(purchaseOrder, writer);
        return writer.toString();
    }
}