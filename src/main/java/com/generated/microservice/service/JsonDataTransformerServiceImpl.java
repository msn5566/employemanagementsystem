package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {



    @Override
    public PurchaseOrder transform(Order sourceOrder, String mappingJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            XmlMapper xmlMapper = new XmlMapper();

            JsonNode mapping = objectMapper.readTree(mappingJson);
            PurchaseOrder purchaseOrder = xmlMapper.readValue(mapping.toString(), PurchaseOrder.class);
            // further implementation will follow to populate from mapping json

            return purchaseOrder;
        } catch (Exception e) {
            log.error("Error during transformation: {}", e.getMessage());
            return null;
        }
    }

    private String marshalTargetXml(PurchaseOrder purchaseOrder) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseOrder.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(purchaseOrder, stringWriter);
        return stringWriter.toString();
    }



}