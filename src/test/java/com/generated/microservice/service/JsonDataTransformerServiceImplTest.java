
package com.generated.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JsonDataTransformerServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldTransform_whenValidInput() throws JAXBException, IOException {
        String sourceXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<order>\n" +
                "    <orderId>123</orderId>\n" +
                "    <orderDate>2024-07-24</orderDate>\n" +
                "    <customer>\n" +
                "        <customerId>456</customerId>\n" +
                "        <name>Test Customer</name>\n" +
                "        <contact>\n" +
                "            <email>test@example.com</email>\n" +
                "            <phone>123-456-7890</phone>\n" +
                "        </contact>\n" +
                "    </customer>\n" +
                "    <items>\n" +
                "        <item>\n" +
                "            <itemId>789</itemId>\n" +
                "            <description>Test Item</description>\n" +
                "            <quantity>2</quantity>\n" +
                "            <price>10.50</price>\n" +
                "        </item>\n" +
                "    </items>\n" +
                "    <billing>\n" +
                "        <customerId>456</customerId>\n" +
                "        <totalAmount>21.00</totalAmount>\n" +
                "        <paid>true</paid>\n" +
                "    </billing>\n" +
                "</order>";
        String mappingJson = "{}"; // Empty mapping for this test

        when(objectMapper.readTree(mappingJson)).thenReturn(null); // Mocking since mapping isn't used in this specific test

        String result = jsonDataTransformerService.transform(sourceXml, mappingJson);

        assertNotNull(result);

        // You can add more specific assertions here if needed, by parsing the resulting XML
        // and checking specific values. However, without knowing the exact expected XML,
        // it's hard to provide a concrete assertion.
    }


    @Test
    void shouldThrowJAXBException_whenUnmarshalFails() {
        String sourceXml = "invalid xml";
        String mappingJson = "{}";

        when(objectMapper.readTree(mappingJson)).thenReturn(null);


        assertThrows(JAXBException.class, () -> {
            jsonDataTransformerService.transform(sourceXml, mappingJson);
        });
    }


    @Test
    void shouldThrowIOException_whenMappingJsonIsInvalid() throws JAXBException, IOException {
        String sourceXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<order>\n" +
                "    <orderId>123</orderId>\n" +
                "    <orderDate>2024-07-24</orderDate>\n" +
                "    <customer>\n" +
                "        <customerId>456</customerId>\n" +
                "        <name>Test Customer</name>\n" +
                "        <contact>\n" +
                "            <email>test@example.com</email>\n" +
                "            <phone>123-456-7890</phone>\n" +
                "        </contact>\n" +
                "    </customer>\n" +
                "    <items>\n" +
                "        <item>\n" +
                "            <itemId>789</itemId>\n" +
                "            <description>Test Item</description>\n" +
                "            <quantity>2</quantity>\n" +
                "            <price>10.50</price>\n" +
                "        </item>\n" +
                "    </items>\n" +
                "    <billing>\n" +
                "        <customerId>456</customerId>\n" +
                "        <totalAmount>21.00</totalAmount>\n" +
                "        <paid>true</paid>\n" +
                "    </billing>\n" +
                "</order>"; // Valid minimal XML
        String mappingJson = "invalid json";

        when(objectMapper.readTree(mappingJson)).thenThrow(new IOException("Invalid JSON"));

        assertThrows(IOException.class, () -> jsonDataTransformerService.transform(sourceXml, mappingJson));

    }


}