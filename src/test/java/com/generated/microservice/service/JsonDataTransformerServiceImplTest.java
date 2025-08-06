package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.*;
import com.generated.microservice.model.target.PurchaseOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JsonDataTransformerServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    private Order order;
    private JsonNode mapping;


    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderId("12345");
        order.setOrderDate("2024-07-24");

        Customer customer = new Customer();
        customer.setCustomerId("CUST001");
        customer.setName("John Doe");

        Contact contact = new Contact();
        contact.setEmail("john.doe@example.com");
        contact.setPhone("123-456-7890");
        customer.setContact(contact);
        order.setCustomer(customer);


        Items items = new Items();
        Item item1 = new Item();
        item1.setDescription("Product A");
        item1.setQuantity(2);
        item1.setPrice(10.00);

        Item item2 = new Item();
        item2.setDescription("Product B");
        item2.setQuantity(1);
        item2.setPrice(20.00);
        items.setItem(List.of(item1,item2));
        order.setItems(items);


        Billing billing = new Billing();
        billing.setCustomerId("CUST001");
        billing.setTotalAmount(40.00);
        billing.setPaid(true);

        order.setBilling(billing);


    }


    @Test
    void shouldTransformPOJO_whenValidOrderAndMappingProvided() {
        com.generated.microservice.model.target.PurchaseOrder purchaseOrder = jsonDataTransformerService.transformPOJO(order, mapping);

        assertEquals("12345", purchaseOrder.getId());
        assertEquals("2024-07-24", purchaseOrder.getDate());
        assertEquals("John Doe", purchaseOrder.getClient().getFullName());
        assertEquals("john.doe@example.com", purchaseOrder.getClient().getEmail());
        assertEquals("123-456-7890", purchaseOrder.getClient().getPhone());

        List<com.generated.microservice.model.target.Product> products = purchaseOrder.getProducts().getProduct();

        assertEquals(2, products.size());

        assertEquals("Product A", products.get(0).getName());
        assertEquals(2, products.get(0).getQty());
        assertEquals(BigDecimal.valueOf(10.00), products.get(0).getUnitPrice());


        assertEquals("Product B", products.get(1).getName());
        assertEquals(1, products.get(1).getQty());
        assertEquals(BigDecimal.valueOf(20.00), products.get(1).getUnitPrice());


        assertEquals("CUST001", purchaseOrder.getPayment().getCustomerId());
        assertEquals(BigDecimal.valueOf(40.00), purchaseOrder.getPayment().getAmount());
        assertEquals("Paid", purchaseOrder.getPayment().getStatus());

        Mockito.verifyNoMoreInteractions(objectMapper);


    }


    @Test
    void shouldReturnPaid_whenBillingIsPaid() {

        String paidStatus = jsonDataTransformerService.booleanToPaidStatus(true);
        assertEquals("Paid",paidStatus);
        Mockito.verifyNoMoreInteractions(objectMapper);


    }

    @Test
    void shouldReturnUnPaid_whenBillingIsNotPaid() {

        String paidStatus = jsonDataTransformerService.booleanToPaidStatus(false);
        assertEquals("Unpaid",paidStatus);
        Mockito.verifyNoMoreInteractions(objectMapper);


    }

    @Test
    void testTransform() throws JAXBException, IOException {

        String sourceXml = "<Order><OrderId>ORD12345</OrderId><OrderDate>2024-07-24</OrderDate><Customer><CustomerId>CUST001</CustomerId><Name>John Doe</Name><Contact><Email>john.doe@example.com</Email><Phone>123-456-7890</Phone></Contact></Customer><Items><Item><ItemId>ITEM001</ItemId><Description>Product A</Description><Quantity>2</Quantity><Price>10.00</Price></Item><Item><ItemId>ITEM002</ItemId><Description>Product B</Description><Quantity>1</Quantity><Price>20.00</Price></Item></Items><Billing><CustomerId>CUST001</CustomerId><TotalAmount>40.00</TotalAmount><Paid>true</Paid></Billing></Order>";
        String mappingJson = "{}";


        when(objectMapper.readTree(mappingJson)).thenReturn(mock(com.fasterxml.jackson.databind.JsonNode.class));

        String transformedXml = jsonDataTransformerService.transform(sourceXml, mappingJson);

        JAXBContext targetContext = JAXBContext.newInstance(PurchaseOrder.class);
        Unmarshaller unmarshaller = targetContext.createUnmarshaller();
        PurchaseOrder targetOrder = (PurchaseOrder) unmarshaller.unmarshal(new StringReader(transformedXml));
        assertEquals("ORD12345", targetOrder.getId());



    }

    // Helper function to normalize XML strings for comparison
    private String normalizeXml(String xml) {
        return xml.replaceAll("\\s+", ""); // Remove all whitespace
    }



        @Test
        void testTransform_JAXBException() throws JAXBException {


        }



    @Test
    void testTransform_IOException() throws IOException {


    }
}