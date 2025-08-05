package com.generated.microservice.service;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JsonDataTransformerServiceImplTest {

    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @Test
    void shouldTransformToPurchaseOrder_whenValidXmlAndMappingProvided() throws JAXBException {
        String xmlSource = "<Order><orderId>123</orderId></Order>";
        String mappingJson = "{}"; // Empty mapping for this basic test

        PurchaseOrder purchaseOrder = jsonDataTransformerService.transformToPurchaseOrder(xmlSource, mappingJson);

        assertEquals("123", purchaseOrder.getId());
    }


    @Test
    void shouldReturnNull_whenInvalidXmlProvided() {
        String invalidXmlSource = "<Order><orderId>123</invalidXml>"; // Missing closing tag
        String mappingJson = "{}";

        PurchaseOrder purchaseOrder = jsonDataTransformerService.transformToPurchaseOrder(invalidXmlSource, mappingJson);

        assertNull(purchaseOrder);
    }


    @Test
    void shouldReturnNull_whenJAXBExceptionOccurs() throws JAXBException {
        String xmlSource = "<InvalidOrder></InvalidOrder>"; // Invalid XML structure
        String mappingJson = "{}";

        PurchaseOrder purchaseOrder = jsonDataTransformerService.transformToPurchaseOrder(xmlSource, mappingJson);
        assertNull(purchaseOrder);

    }



}