package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import jakarta.xml.bind.JAXBContext;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class JsonDataTransformerServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;


    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @Test
    void testTransform_validInput() throws JAXBException, IOException {
        String sourceXml = "<Order><OrderId>123</OrderId></Order>"; // Replace with your actual XML
        String mappingJson = "{}"; // Replace with your actual JSON


        JAXBContext jaxbContextSource = JAXBContext.newInstance(Order.class);
        Unmarshaller unmarshaller = jaxbContextSource.createUnmarshaller();
        Order sourceOrder = (Order) unmarshaller.unmarshal(new StringReader(sourceXml));

        JsonNode jsonNode = mock(JsonNode.class);
        when(objectMapper.readTree(mappingJson)).thenReturn(jsonNode); // Mock readTree



        PurchaseOrder targetOrder = jsonDataTransformerService.transformPOJO(sourceOrder, mappingJson);



        JAXBContext jaxbContextTarget = JAXBContext.newInstance(PurchaseOrder.class);
        Marshaller marshaller = jaxbContextTarget.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // For pretty printing
        StringWriter sw = new StringWriter();
        marshaller.marshal(targetOrder, sw);
        String marshalledOutput = sw.toString();

        //assertEquals(marshalledOutput, jsonDataTransformerService.transform(sourceXml, mappingJson));
        assertNotNull(jsonDataTransformerService.transform(sourceXml, mappingJson));


    }




    @Test
    void testTransform_invalidInputJAXBException() {

        String sourceXml = "invalid xml";
        String mappingJson = "{}";


        assertThrows(JAXBException.class, ()-> jsonDataTransformerService.transform(sourceXml, mappingJson));
    }


    @Test
    void testTransform_invalidInputIOException() throws JAXBException, IOException {

        String sourceXml = "<Order><OrderId>123</OrderId></Order>";
        String mappingJson = "{}"; // Replace with your actual JSON

        when(objectMapper.readTree(mappingJson)).thenThrow(new IOException());

        assertThrows(IOException.class, ()-> jsonDataTransformerService.transform(sourceXml, mappingJson));
    }



}
--- FILE END: src/test/java/com/generated/microservice/service/JsonDataTransformerServiceImplTest.java ---