
package com.generated.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public String transform(String xmlData, String mappingJson) throws IOException {
       //Existing Implementation
        return null;
    }

    @Override
    public PurchaseOrder transformPOJO(Order order, String mappingJson) {
        //Existing Implementation
        return null;
    }


    @Override
    public PurchaseOrder transformFromFiles(MultipartFile sourceXmlFile, MultipartFile targetXmlFile, MultipartFile mappingJsonFile) throws IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Order sourceOrder = (Order) unmarshaller.unmarshal(sourceXmlFile.getInputStream());


            String mappingJson = new String(mappingJsonFile.getBytes());
            PurchaseOrder targetPurchaseOrder = transformPOJO(sourceOrder, mappingJson);

            return targetPurchaseOrder;

        }  catch (JAXBException e) {
            log.error("Error during XML unmarshalling: {}", e.getMessage());
            throw new IOException("Failed to unmarshal XML", e);
        }
    }

    // Helper functions
    private String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }
}